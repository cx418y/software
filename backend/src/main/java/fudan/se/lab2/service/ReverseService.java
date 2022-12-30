package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.ReturnBookRequest;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.exception.*;
import fudan.se.lab2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReverseService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private ReversionRepository reversionRepository;
    @Autowired
    private AreaService areaService;
    @Autowired
    private BookStateService bookStateService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private FineRepository fineRepository;
    @Autowired
    private CreditRecordRepository creditRecordRepository;


    public boolean reverse(ReturnBookRequest request) throws ParseException {
        StringBuilder message = new StringBuilder("");
        int fineAmount = 0;
        for (int i = 0; i < request.getIsbns().length; i++) {
            String isbn = request.getIsbns()[i];
            Book book = bookRepository.findBookByIsbn(isbn);
            Borrow borrowBook = borrowRepository.findBorrowByBookAndReverseIs(book,0);

            //isbn错误：
            if (book == null) throw new BookNotFoundException("Book " + isbn + " not found!");
            BookState bookState = book.getBookState();

            if (borrowBook == null) {
                throw new BookNotFoundException("Book has not been borrowed, it is " + bookState.getName() + "!");

            }
            //图书状态为被借阅
            if (bookState.getId() == 2) {
                if (request.getAdminName() == "" || request.getUserName() == "" || request.getReturnAid() == 0 || request.getReturnAid() > 6 || isbn == "") {
                    throw new InformationMissingException("return information");
                } else {
                    User returnUser = userRepository.findByUsername(request.getUserName());
                    Librarian librarian = librarianRepository.findByUsername(request.getUserName());

                    if (librarian != null) {
                        throw new HaveNoAuthorityException("Don't have return authority!");
                    }

                    if (returnUser == null) {
                        throw new UserNotFoundException("User " + request.getUserName());
                    }

                    User borrowUser = userRepository.findByUsername(borrowBook.getUserName());
                    Date date = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    String returnTime = df.format(date); // 还书日期

                    String borrowedTime = borrowBook.getBorrowTime(); //借书日期
                    String endTime = borrowBook.getEndTime();  //借书到期时间

                    String borrowUsername = borrowUser.getUsername();
                    Area borrowedArea = areaService.get(borrowBook.getArea().getId());//借书校区
                    Area returnArea = areaService.get(request.getReturnAid());  //还书校区

                    double price = book.getPrice();
                    Date date1 = df.parse(endTime);
                    Date date2 = df.parse(returnTime);

                    String borrowAdmin = borrowBook.getAdminName();
                    //图书状态正常
                    if(request.getStates()[i].equals("good")) {
                        Reversion reversionBook = new Reversion(book,borrowUsername,request.getUserName(), borrowAdmin,request.getAdminName(),borrowedTime, returnTime, borrowedArea, returnArea,"normal");
                        //借阅超时
                        if(date1.compareTo(date2)<0) {
                            Fine fine = new Fine(borrowUsername, request.getAdminName(),isbn, "borrow overtime", price / 4.0,returnArea,returnTime);
                            fineRepository.save(fine);
                            fineAmount += price / 4.0;

                            //用户信誉分减20
                            //更新用户信息
                            borrowUser.setBorrowNum(borrowUser.getBorrowNum()-1);
                            borrowUser.setCredit(borrowUser.getCredit()-20);
                            userRepository.save(borrowUser);

                            User user = userRepository.findByUsername(borrowUser.getUsername());
                            CreditRecord creditRecord = new CreditRecord(borrowUsername,request.getAdminName(),-20,"borrow overtime",returnTime,isbn,user.getCredit());
                            creditRecordRepository.save(creditRecord);

                            message.append("book " + isbn + " borrowed overtime, fine " + price / 4.0 + " yuan! user "+borrowUsername+" credit reduce 20, current credit is: "+borrowUser.getCredit()+"\n");
                            reversionBook.setOvertime(1);
                            borrowBook.setOvertime(1);
                            reversionBook.setState("overtime");
                        }

                        //存入归还图书表:
                        else {
                            borrowBook.setOvertime(0);
                            reversionBook.setOvertime(0);
                        }
                        reversionRepository.save(reversionBook);

                        //更新图书状态为在库
                        bookState = bookStateService.get(0);
                        book.setBookState(bookState);
                        Area a = areaService.get(request.getReturnAid());
                        book.setArea(a);
                        bookRepository.save(book);

                        //更新借书表
                        borrowBook.setReverse(1);
                        borrowRepository.save(borrowBook);


                    }
                    //图书损毁
                    else if(request.getStates()[i].equals("damaged")){
                        //罚款：
                        Fine fine = new Fine(borrowUsername, request.getAdminName(),isbn, "book damage", price / 2.0,returnArea,returnTime);
                        fineRepository.save(fine);
                        fineAmount += price / 2.0;

                        Reversion reversionBook = new Reversion(book,borrowUsername,request.getUserName(), borrowAdmin,request.getAdminName(),borrowedTime, returnTime, borrowedArea, returnArea,"damaged");

                        if(date1.compareTo(date2)<0) {
                            reversionBook.setOvertime(1);
                            borrowBook.setOvertime(1);
                        }

                        //存入归还图书表:
                        else {
                            borrowBook.setOvertime(0);
                            reversionBook.setOvertime(0);
                        }

                        borrowBook.setReverse(1);
                        borrowRepository.save(borrowBook);
                        reversionRepository.save(reversionBook);

                        //更新图书状态
                        bookState = bookStateService.get(3);
                        book.setBookState(bookState);
                        bookRepository.save(book);

                        //更新用户信息
                        //用户信誉分减30
                        borrowUser.setCredit(borrowUser.getCredit()-30);
                        message.append("book " + isbn + " is damaged, fine " + price / 2.0 + " yuan! user "+borrowUsername+" credit reduce 30, current credit is: "+borrowUser.getCredit()+"\n");
                        borrowUser.setBorrowNum(borrowUser.getBorrowNum()-1);
                        userRepository.save(borrowUser);

                        User user = userRepository.findByUsername(borrowUser.getUsername());
                        CreditRecord creditRecord = new CreditRecord(borrowUsername,request.getAdminName(),-30,"book damage",returnTime,isbn,user.getCredit());
                        creditRecordRepository.save(creditRecord);
                    }else{   //遗失
                        //罚款：
                        Fine fine = new Fine(borrowUsername, request.getAdminName(),isbn,"book missing", price,returnArea,returnTime);
                        fineRepository.save(fine);
                        fineAmount += price;


                        //message.append("book " + isbn + " is missing, fine " + price + " yuan!\n");

                        Reversion reversionBook = new Reversion(book,borrowUsername,request.getUserName(), borrowAdmin,request.getAdminName(),borrowedTime, returnTime, borrowedArea, returnArea,"missing");

                        if(date1.compareTo(date2)<0) {
                            reversionBook.setOvertime(1);
                            borrowBook.setOvertime(1);
                        }

                        //存入归还图书表:
                        else {
                            borrowBook.setOvertime(0);
                            reversionBook.setOvertime(0);
                        }

                        borrowBook.setReverse(1);
                        borrowRepository.save(borrowBook);
                        reversionRepository.save(reversionBook);

                        //更新图书状态
                        bookState = bookStateService.get(4);
                        book.setBookState(bookState);
                        bookRepository.save(book);

                        //更新用户信息
                        borrowUser.setCredit(borrowUser.getCredit()-40);


                        message.append("book " + isbn + " is missing, fine " + price + " yuan! user "+borrowUsername+" credit reduce 40, current credit is: "+borrowUser.getCredit()+"\n");

                        borrowUser.setBorrowNum(borrowUser.getBorrowNum()-1);
                        userRepository.save(borrowUser);

                        User user = userRepository.findByUsername(borrowUser.getUsername());
                        CreditRecord creditRecord = new CreditRecord(borrowUsername,request.getAdminName(),-40,"book missing",returnTime,isbn,borrowUser.getCredit());
                        creditRecordRepository.save(creditRecord);
                    }
                }
            } else {
                throw new BookNotFoundException("Book has not been borrowed, it is " + bookState.getName() + "!");
            }
        }
        if(fineAmount>0){
            throw new ReturnBookFineException(message.toString());
        }
        return true;
    }
}

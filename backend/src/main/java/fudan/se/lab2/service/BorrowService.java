package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.BorrowBookRequest;
import fudan.se.lab2.domain.*;

import fudan.se.lab2.exception.*;
import fudan.se.lab2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service

public class BorrowService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowTableRepository;
    @Autowired
    private AreaService areaService;
    @Autowired
    private BookStateService bookStateService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LibrarianRepository librarianRepository;


    //图书借阅
    public boolean borrow(BorrowBookRequest request) {
        User user = userRepository.findByUsername(request.getUserName());
        Librarian librarian = librarianRepository.findByUsername(request.getUserName());
        if (librarian != null) {
            throw new HaveNoAuthorityException("Don't have borrow authority!");
        }
        if (user == null) {
            throw new UserNotFoundException(request.getUserName());
        }

        //用户信誉分不够
        if(user.getCredit()<0){
            throw new UserCreditNotEnoughException("Credit not enough,current credit is :" + user.getCredit());
        }

        //借阅本数超过最大数量
        Authority authority = user.getAuthority();
        if((user.getBorrowNum()+user.getReserveNum()+request.getIsbns().length)>authority.getMostBorrow()){
            throw new BorrowAmountsExceedMaximumException(user.getUsername(),authority.getMostBorrow()-user.getBorrowNum()-user.getReserveNum());
        }

        //地区不匹配的数量，以及isbn
        int flag = 0;
        StringBuilder isbns = new StringBuilder("");

        for (int i = 0; i < request.getIsbns().length; i++) {
            String isbn = request.getIsbns()[i];
            Book book = bookRepository.findBookByIsbn(isbn);
            if (book == null) throw new BookNotFoundException("Book " + isbn + " not found!");
            if (book.getIsCopy() == 0) {
                throw new HaveNoAuthorityException("Don't have authority");
            }
            BookState bookState = book.getBookState();
            //图书可以借阅
            if (bookState.getId() == 0) {
                if (request.getAdminName() == "" || request.getUserName() == "" || request.getAid() == 0 || isbn == "") {
                    throw new InformationMissingException("information");
                } else {

                    //地区不匹配
                    if (book.getArea().getId() != request.getAid()) {
                        flag++;
                        isbns.append(book.getIsbn()+" ");
                    }else {  //成功借出
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        //String dateTime = df.format(date); // Formats a Date into a date/time string.

                        Date borrowTime = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(borrowTime);

                        long borrowOvertime = user.getAuthority().getBorrowOvertime();
                        int[] array = new int[4];
                        array[0] = (int) (borrowOvertime / 86400);
                        array[1] = (int) ((borrowOvertime % 86400) / 3600);
                        array[2] = (int) ((borrowOvertime % 86400 % 3600) / 60);
                        array[3] = (int) (borrowOvertime % 86400 % 3600 % 60);
                        //借书最迟归还时间
                        calendar.add(Calendar.SECOND, array[3]);
                        calendar.add(Calendar.MINUTE, array[2]);
                        calendar.add(Calendar.HOUR_OF_DAY, array[1]);
                        calendar.add(Calendar.DAY_OF_YEAR, array[0]);

                        Date endTime = calendar.getTime();

                        Area area = areaService.get(request.getAid());  //图书所在校区

                        Borrow borrowTable = new Borrow(book, area, request.getUserName(), request.getAdminName(), df.format(borrowTime), df.format(endTime));

                        borrowTableRepository.save(borrowTable);

                        //更新图书状态
                        bookState = bookStateService.get(2);
                        book.setBookState(bookState);
                        bookRepository.save(book);

                        //更新用户借书数量
                        user.setBorrowNum(user.getBorrowNum() + 1);
                        userRepository.save(user);
                    }

                }
            }

            //图书已被预约或已损毁
            else if (bookState.getId() == 1) {
                throw new BookNotFoundException("Book has been reserved by others!");
            } else if (bookState.getId() == 2) {
                throw new BookNotFoundException("Book has been borrowed by others!");
            } else {
                throw new BookNotFoundException("Book has damaged!");
            }
        }
        if(flag > 0){
            throw new BookNotFoundException(isbns.toString()+"areas not matching!");
        }
        return true;
    }
}

package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.GetReserveRequest;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.exception.BookNotAvailableException;
import fudan.se.lab2.exception.BorrowAmountsExceedMaximumException;
import fudan.se.lab2.exception.UserCreditNotEnoughException;
import fudan.se.lab2.repository.*;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReservedService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReservedRepository reservedRepository;
    @Autowired
    private AreaService areaService;

    @Autowired
    private BookStateService bookStateService;
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserRepository userRepository;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

    @Autowired
    private CreditRecordRepository creditRecordRepository;


    @Autowired
    public ReservedService(){}
    public ReservedService(BookRepository bookRepository, ReservedRepository reservedRepository, BorrowRepository b) {
        this.bookRepository = bookRepository;
        this.reservedRepository = reservedRepository;
        this.borrowRepository = b;

    }
    public String reserve(String isbn, String username){
        Book book = bookRepository.findByIsbn(isbn);
        User user = userRepository.findByUsername(username);

        if((user.getReserveNum()+user.getBorrowNum())>=user.getAuthority().getMostBorrow()){
            throw new BorrowAmountsExceedMaximumException(username,0);
        }


        //用户信誉分不够
        if(user.getCredit()<50){
            throw new UserCreditNotEnoughException("Credit not enough,current credit is: " + user.getCredit());
        }

        if(book.getBookState().getId() == 0){
            BookState bookState = bookStateService.get(1);
            book.setBookState(bookState);//reserved
            bookRepository.save(book);
            Date d = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);

            long reserveOvertime = user.getAuthority().getReserveOvertime();
            int[]array = new int[4];
            array[0] = (int)(reserveOvertime/ 86400);
            array[1] = (int)((reserveOvertime % 86400) / 3600);
            array[2] = (int)((reserveOvertime % 86400 % 3600) / 60);
            array[3] = (int)(reserveOvertime % 86400 % 3600 % 60);
            //借书最迟归还时间
            calendar.add(Calendar.SECOND, array[3]);
            calendar.add(Calendar.MINUTE, array[2]);
            calendar.add(Calendar.HOUR_OF_DAY, array[1]);
            calendar.add(Calendar.DAY_OF_YEAR, array[0]);

            Date date = calendar.getTime();
            Reserved reserved = new Reserved(sdf.format(d), sdf.format(date), username, book,0,0);
            reservedRepository.save(reserved);

            user.setReserveNum(user.getReserveNum()+1);
            userRepository.save(user);

            return "Reserved Successfully!";
        }else{
            throw new BookNotAvailableException(isbn);
        }
    }

    public List<Reserved> reservedSearch(String username){
        //查找用户预约但还没有取的书
        List<Reserved> reserveds = reservedRepository.findAllByUsernameAndBorrow(username,0);
        if(reserveds != null){
            return reserveds;
        }else {
            throw new BadCredentialsException("No reservations!");
        }
    }

    public String fetchReserved(GetReserveRequest request) throws ParseException {
        String[] isbn = request.getIsbn();
        int num = isbn.length;
        String username = request.getUserName();
        String librarian = request.getAdminName();
        Area area = areaService.get(request.getAid());

        User user = userRepository.findByUsername(username);

        if(user.getCredit()<0){
            throw new UserCreditNotEnoughException("Credit not enough,current credit is :" + user.getCredit());
        }

        int flag1 = 0;
        int flag2 = 0;
        StringBuilder isbns = new StringBuilder("");
        StringBuilder areas = new StringBuilder("");

        for(int i = 0; i < num; i++){
            String isbnStr = isbn[i];
            Book book = bookRepository.findByIsbn(isbnStr);
            Reserved reserved = reservedRepository.findByBookAndBorrowIs(book,0);
            if(reserved != null) {
                // reservedRepository.delete(reserved);
                Date borrowTime = new Date();

                if(sdf.parse(reserved.getEndTime()).compareTo(borrowTime) < 0){
                    if(reserved.getOvertime() == 0){  // 预约已经超时但还未提醒
                        //用户信誉分变化
                        user.setCredit(user.getCredit()-10);
                        CreditRecord creditRecord = new CreditRecord(user.getUsername(),request.getAdminName(),-10,"book damage",sdf.format(borrowTime),isbn[i],user.getCredit());
                        creditRecordRepository.save(creditRecord);

                        reserved.setOvertime(1);
                        user.setReserveNum(user.getReserveNum()-1);
                        userRepository.save(user);
                        BookState bookState = bookStateService.get(0);
                        book.setBookState(bookState);
                        bookRepository.save(book);
                    }

                }

                if (reserved.getOvertime() == 0) {
                    if(request.getAid()==book.getArea().getId()) {
                        reserved.setBorrow(1);
                        reservedRepository.save(reserved);
                        BookState bookState = bookStateService.get(2);

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

                        Borrow borrow = new Borrow(book, area, username, librarian, sdf.format(borrowTime), sdf.format(endTime));
                        borrowRepository.save(borrow);
                        book.setBookState(bookState);
                        bookRepository.save(book);

                        user.setBorrowNum(user.getBorrowNum() + 1);
                        user.setReserveNum(user.getReserveNum() - 1);
                        userRepository.save(user);
                    }else{
                        flag2++;
                        areas.append(book.getIsbn()+"  ");
                    }
                }else{
                    flag1++;
                    isbns.append(book.getIsbn()+"  ");
                    reserved.setBorrow(1);
                    reservedRepository.save(reserved);
                   // throw  new BadCredentialsException("reservation is expired!");

                }
            }else{
                throw  new BadCredentialsException("No reservation record!");
            }
        }
        if(flag1 > 0 && flag2 > 0){
            throw new BadCredentialsException(isbns.toString()+"reservation are expired! " +areas.toString()+"area not matching" );
        }
        if(flag1 > 0){
            throw new BadCredentialsException(isbns.toString()+"reservation are expired!");
        }
        if(flag2 > 0){
            throw new BadCredentialsException(areas.toString()+"area not matching");
        }
        return "Fetch Successfully!";
    }
}

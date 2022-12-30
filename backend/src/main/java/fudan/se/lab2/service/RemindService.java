package fudan.se.lab2.service;

import fudan.se.lab2.Assist.MailUtil;
import fudan.se.lab2.Assist.RemindRecord;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.repository.*;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RemindService {
    @Autowired
    private FineRepository fineRepository;
    @Autowired
    private ReservedRepository reservedRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookStateService bookStateService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreditRecordRepository creditRecordRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式




    public List<RemindRecord> remind() throws ParseException {
        List<RemindRecord> recordList = new ArrayList<>();


        List<String> username = new ArrayList<>();

        List<Reserved> reservedList = reservedRepository.findAllByOvertimeAndBorrow(0, 0);
        Date nowTime = new Date();
        if(reservedList != null ){
            int len = reservedList.size();
            for(int i = 0; i < len; i++){
                Reserved r = reservedList.get(i);
                if(sdf.parse(r.getEndTime()).compareTo(nowTime) < 0){
                    r.setOvertime(1);
                    reservedRepository.save(r);
                    User user = userRepository.findByUsername(r.getUsername());
                    //用户信誉分变化
                    user.setCredit(user.getCredit()-10);
                    CreditRecord creditRecord = new CreditRecord(user.getUsername(),"admin",-10,"book damage",sdf.format(nowTime),r.getBook().getIsbn(),user.getCredit());
                    creditRecordRepository.save(creditRecord);

                    if(!username.contains(r.getUsername()))
                        username.add(r.getUsername());

                }
            }

        }


        List<Borrow> borrowList = borrowRepository.findAllByOvertimeAndReverse(0, 0);
        if(borrowList != null) {
            int len = borrowList.size();
            for (int i = 0; i < len; i++) {
                Borrow b = borrowList.get(i);
                if (sdf.parse(b.getEndTime()).compareTo(nowTime) < 0) {
                    b.setOvertime(1);
                    borrowRepository.save(b);
                    if(!username.contains(b.getUserName()))
                        username.add(b.getUserName());
                }
            }

        }

        List<Fine> fineList = fineRepository.findAllByPaid(0);
        if(fineList != null) {
            int len = fineList.size();
            for (int i = 0; i < len; i++) {
                Fine f = fineList.get(i);
                if(!username.contains(f.getUsername()))
                    username.add(f.getUsername());

            }

        }

        int len = username.size();
        for(int i = 0; i < len; i++){
            String u = username.get(i);
            String[] borrowOvertime = {"No"};
            String[] reserveOvertime = {"No"};
            String[] unpaid = {"No"};

            List<Borrow> borrows = borrowRepository.findAllByUserNameAndOvertimeAndReverse(u, 1, 0);
            if(borrows != null){
                int num = borrows.size();
               borrowOvertime = new String[num];
                for(int j = 0; j < num; j++){
                    borrowOvertime[j] = borrows.get(j).getBook().getIsbn();
                }
            }

            List<Reserved> reserveds =reservedRepository.findAllByUsernameAndOvertimeAndBorrow(u, 1, 0);
            if(reserveds != null){
                int num = reserveds.size();
                reserveOvertime = new String[num];
                for(int j = 0; j < num; j++){
                    reserveOvertime[j] = reserveds.get(j).getBook().getIsbn();
                }
            }

            List<Fine> fines = fineRepository.findAllByUsernameAndPaid(u, 0);
            if(fines != null){
                int num = fines.size();
                unpaid = new String[num];
                for(int j = 0; j < num; j++){
                    unpaid[j] = fines.get(j).getIsbn();
                }
            }
            RemindRecord remindRecord = new RemindRecord(u, reserveOvertime, borrowOvertime, unpaid);
            recordList.add(remindRecord);

        }


        return recordList;

    }

    public String sendRemind(String[] usernames) throws EmailException {
        String content = "";
        int length = usernames.length;
        int len = 0;
        String username = "";
        for(int i = 0; i < length; i++){
            content = "";
            username = usernames[i];
            List<Fine> fine = fineRepository.findAllByUsernameAndPaid(username, 0);
            if(fine != null) {
                len = fine.size();
                for (int j = 0; j < len; j++) {
                    Fine f = fine.get(j);
                    content += "Book:" + f.getIsbn() + " Reason:" + f.getReason() + "Fine:" + f.getPrice() + "\n";
                }
            }


            List<Reserved> reserveds = reservedRepository.findAllByUsernameAndOvertimeAndBorrow(username, 1, 0);
            if(reserveds != null) {
                len = reserveds.size();
                for (int j = 0; j < len; j++) {
                    Reserved r = reserveds.get(j);
                    content += "Book:" + r.getBook().getTitle() + " is supposed to be borrowed before " +
                            r.getEndTime() + "\n";
                    r.setBorrow(1);
                    reservedRepository.save(r);
                    User user = userRepository.findByUsername(r.getUsername());
                    user.setReserveNum(user.getReserveNum() - 1);
                    userRepository.save(user);

                    Book book = bookRepository.findByIsbn(r.getBook().getIsbn());
                    BookState bookState = bookStateService.get(0);
                    book.setBookState(bookState);
                    bookRepository.save(book);
                }
            }

            List<Borrow> borrow = borrowRepository.findAllByUserNameAndOvertimeAndReverse(username, 1, 0);
            if(borrow != null) {
                len = borrow.size();
                for (int j = 0; j < len; j++) {
                    Borrow b = borrow.get(j);
                    content += "Book:" + b.getBook().getTitle() + " is supposed to be reserved before " +
                            b.getEndTime() + "\n";
                }
            }
            String recipient = username + "@fudan.edu.cn";
            System.out.println(username);
            MailBean mailBean = new MailBean(recipient,
                    "Library Remind",
                    content);
            MailUtil.sendSimpleTextEmail(mailBean);


        }
        return "send successfully!";

    }



}

package fudan.se.lab2.service;

import fudan.se.lab2.Assist.AdminRecord;
import fudan.se.lab2.Assist.CopyRecord;
import fudan.se.lab2.Assist.UserFineRecord;
import fudan.se.lab2.Assist.UserRecord;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.exception.BookNotFoundException;
import fudan.se.lab2.exception.UserNotFoundException;
import fudan.se.lab2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RecordService {

    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    ReversionRepository reversionRepository;
    @Autowired
    ReservedRepository reservedRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LibrarianRepository librarianRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    FineRepository fineRepository;

    @Autowired
    private CreditRecordRepository creditRecordRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式



    public List<CopyRecord> getCopyRecord(String isbn){
        Book book = bookRepository.findBookByIsbn(isbn);
        if(book == null){
            throw new BookNotFoundException("Book " + isbn + " not found!");
        }

        List<CopyRecord> list= new ArrayList<>();

        //借出记录：
        List<Borrow> borrows = borrowRepository.findAllByBook(book);
        for(int i = 0;i<borrows.size();i++){
            Borrow a = borrows.get(i);
            CopyRecord borrow = new CopyRecord(a.getBorrowTime(),"Borrow",a.getAdminName(),a.getUserName(),a.getArea());
            list.add(borrow);
        }

        //归还记录：
        List<Reversion> reverses = reversionRepository.findAllByBook(book);
        for(int i = 0;i<reverses.size();i++){
            Reversion a = reverses.get(i);
            int sid = a.getBook().getBookState().getId();
            CopyRecord reverse = new CopyRecord(a.getReturnTime(),"Return ("+a.getState()+")",a.getReturnAdmin(),a.getReturnUsername(),a.getReturnArea());
            list.add(reverse);
        }

        //预约记录
        List<Reserved> reserves = reservedRepository.findAllByBook(book);
        for(int i = 0;i<reserves.size();i++){
            Reserved a = reserves.get(i);
            CopyRecord reserve = new CopyRecord(a.getStartTime(),"Reserve"," ",a.getUsername(),null);
            list.add(reserve);
        }

        Collections.sort(list,new Comparator<CopyRecord>()  {
            @Override
            public int compare(CopyRecord c1, CopyRecord c2) {
                String time1 = c1.getTime();
                String time2 = c2.getTime();
                if(time1.compareTo(time2) == 0){
                        return 0;
                }else if(time1.compareTo(time2) < 0){
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        return list;
    }

    public List<UserRecord> getUserRecord(String userName){
        User user = userRepository.findByUsername(userName);
        if(user == null){
            throw new UserNotFoundException("User " + userName);
        }

        List<UserRecord> list= new ArrayList<>();

        //借出记录：
        List<Borrow> borrows = borrowRepository.findAllByUserName(userName);
        for(int i = 0;i<borrows.size();i++){
            Borrow a = borrows.get(i);
            UserRecord borrow = new UserRecord(a.getBorrowTime(),"Borrow",a.getAdminName(),a.getBook().getIsbn(),a.getArea());
            list.add(borrow);
        }

        //归还记录：
        List<Reversion> reverses = reversionRepository.findAllByReturnUsername(userName);
        for(int i = 0;i<reverses.size();i++){
            Reversion a = reverses.get(i);
            UserRecord reverse = new UserRecord(a.getReturnTime(),"Return ("+a.getState()+")",a.getReturnAdmin(),a.getBook().getIsbn(),a.getReturnArea());
            list.add(reverse);
        }

        //预约记录
        List<Reserved> reserves = reservedRepository.findAllByUsername(userName);
        for(int i = 0;i<reserves.size();i++){
            Reserved a = reserves.get(i);
            UserRecord reserve = new UserRecord(a.getStartTime(),"Reserve"," ",a.getBook().getIsbn(),null);
            list.add(reserve);
        }

        Collections.sort(list,new Comparator<UserRecord>() {
            @Override
            public int compare(UserRecord c1, UserRecord c2) {
                String time1 = c1.getTime();
                String time2 = c2.getTime();
                if(time1.compareTo(time2) == 0){
                    return 0;
                }else if(time1.compareTo(time2) < 0){
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        return list;
    }

    public List<AdminRecord> getAdminRecords(){
        List<Librarian> librarians = librarianRepository.findAll();
        List<AdminRecord> list = new ArrayList<>();
        for(int n = 0; n <librarians.size(); n++) {
            //借出记录：
            String adminName = librarians.get(n).getUsername();
            List<Borrow> borrows = borrowRepository.findAllByAdminName(adminName);
            for (int i = 0; i < borrows.size(); i++) {
                Borrow a = borrows.get(i);
                AdminRecord borrow = new AdminRecord(a.getBorrowTime(), "Borrow", a.getBook().getIsbn(), adminName, a.getUserName(), a.getArea());
                list.add(borrow);
            }

            //归还记录：
            List<Reversion> reverses = reversionRepository.findAllByReturnAdmin(adminName);
            for (int i = 0; i < reverses.size(); i++) {
                Reversion a = reverses.get(i);

                AdminRecord reverse = new AdminRecord(a.getReturnTime(), "Return ("+a.getState()+")",a.getBook().getIsbn(), adminName,a.getReturnUsername(),a.getReturnArea());
                list.add(reverse);
            }
        }
        Collections.sort(list,new Comparator<AdminRecord> () {
            @Override
            public int compare(AdminRecord c1, AdminRecord c2) {
                String time1 = c1.getTime();
                String time2 = c2.getTime();
                if(time1.compareTo(time2) == 0){
                    return 0;
                }else if(time1.compareTo(time2) < 0){
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });

        return list;
    }

    public List<UserFineRecord> getUserFineRecord(String userName){
        User user = userRepository.findByUsername(userName);
        if(user == null){
            throw new UserNotFoundException("User " + userName + " not found!");
        }
        List<UserFineRecord> list = new ArrayList<>();
        List<Fine> fines = fineRepository.findAllByUsername(userName);
        for(int i = 0 ; i < fines.size(); i++){
            Fine fine = fines.get(i);
            String paid = "";
            if(fine.getPaid() == 0){
                paid = "NO";
            }else{
                paid = "YES, TIME: "+fine.getPayTime();
            }

            UserFineRecord record = new UserFineRecord(fine.getStartTime(),fine.getPrice(),fine.getAdminName(),fine.getIsbn(),fine.getArea(),fine.getReason(),paid);
            list.add(record);
        }
        Collections.sort(list,new Comparator<UserFineRecord>() {
            @Override
            public int compare(UserFineRecord c1, UserFineRecord c2) {
                String time1 = c1.getTime();
                String time2 = c2.getTime();
                if(time1.compareTo(time2) == 0){
                    return 0;
                }else if(time1.compareTo(time2) < 0){
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        return list;
    }

    public List<CreditRecord> creditRecords(String userName){
        User user = userRepository.findByUsername(userName);
        return creditRecordRepository.findAllByUsername(userName);
    }



}

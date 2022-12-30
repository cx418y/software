package fudan.se.lab2.service;

import fudan.se.lab2.Assist.*;
import fudan.se.lab2.domain.*;
import fudan.se.lab2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserBookInfoService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private ReversionRepository reversionRepository;
    @Autowired
    private ReservedRepository reservedRepository;
    @Autowired
    private BookRepository bookRepository;


    public List<UserReserveNotFetch> getUserReserveNotFetch(String username){

        List<UserReserveNotFetch> list = new ArrayList<>();

        List<Reserved> reserveds = reservedRepository.findAllByUsernameAndBorrow(username,0);

        for(int i = 0; i < reserveds.size(); i++){
            String isbn = reserveds.get(i).getBook().getIsbn();
            String title = reserveds.get(i).getBook().getTitle();
            Area area = reserveds.get(i).getBook().getArea();
            String time = reserveds.get(i).getStartTime();
            String expireTime = reserveds.get(i).getEndTime();
            UserReserveNotFetch a = new UserReserveNotFetch(isbn,title,area,time,expireTime);
            list.add(a);
        }

        Collections.sort(list,new Comparator<UserReserveNotFetch>() {
            @Override
            public int compare(UserReserveNotFetch c1, UserReserveNotFetch c2) {
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

    public List<UserBorrowNotReturn> getUserBorrowNotReturn(String username){

        List<UserBorrowNotReturn> list = new ArrayList<>();

        List<Borrow> borrows= borrowRepository.findAllByUserNameAndReverse(username,0);

        for(int i = 0; i < borrows.size(); i++){
            String isbn = borrows.get(i).getBook().getIsbn();
            String title = borrows.get(i).getBook().getTitle();
            Area area = borrows.get(i).getBook().getArea();
            String time = borrows.get(i).getBorrowTime();
            String expireTime = borrows.get(i).getEndTime();
            String adminName = borrows.get(i).getAdminName();
            UserBorrowNotReturn a = new UserBorrowNotReturn(isbn,title,time,expireTime,area,adminName);
            list.add(a);
        }

        Collections.sort(list,new Comparator<UserBorrowNotReturn>() {
            @Override
            public int compare(UserBorrowNotReturn c1, UserBorrowNotReturn c2) {
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
}

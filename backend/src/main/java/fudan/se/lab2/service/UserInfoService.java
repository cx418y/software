package fudan.se.lab2.service;

import fudan.se.lab2.Assist.UserInfo;
import fudan.se.lab2.domain.Fine;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.FineRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FineRepository fineRepository;

    public UserInfo getUserInfo(String username){
        User user = userRepository.findByUsername(username);
        String email = user.getEmail();
        String authority = user.getAuthority().getAuthority();
        int mostBorrow = user.getAuthority().getMostBorrow();
        long borrowOvertime = user.getAuthority().getBorrowOvertime();
        long reserveOvertime = user.getAuthority().getReserveOvertime();
        int credit = user.getCredit();
        double fine = 0;
        List<Fine> list = fineRepository.findAllByUsernameAndPaid(username,0);
        if(list!=null) {
            for (int i = 0; i < list.size(); i++){
                fine += list.get(i).getPrice();
            }
        }

        UserInfo userInfo = new UserInfo(username,email,authority,mostBorrow,borrowOvertime,reserveOvertime,credit,fine);
        return userInfo;
    }
}

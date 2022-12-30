package fudan.se.lab2.service;

import fudan.se.lab2.Assist.AuthorityTime;
import fudan.se.lab2.domain.Authority;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;

@Service
public class GetTimeService {
    public AuthorityTime getTime(Authority authority){
        long borrowOvertime = authority.getBorrowOvertime();
        long reserveOvertime = authority.getReserveOvertime();

        int mostBorrow = authority.getMostBorrow();
        int []borrowTime = new int[4];
        int []reserveTime = new int[4];

        /*
         day = Convert.ToInt16(t / 86400);
                hour = Convert.ToInt16((t % 86400) / 3600);
                minute = Convert.ToInt16((t % 86400 % 3600) / 60);
                second = Convert.ToInt16(t % 86400 % 3600 % 60);
         */
        borrowTime[0] = (int)(borrowOvertime/ 86400);
        borrowTime[1] = (int)((borrowOvertime % 86400) / 3600);
        borrowTime[2] = (int)((borrowOvertime % 86400 % 3600) / 60);
        borrowTime[3] = (int)(borrowOvertime % 86400 % 3600 % 60);

        reserveTime[0] = (int)(reserveOvertime/ 86400);
        reserveTime[1] = (int)((reserveOvertime % 86400) / 3600);
        reserveTime[2] = (int)((reserveOvertime % 86400 % 3600) / 60);
        reserveTime[3] = (int)(reserveOvertime % 86400 % 3600 % 60);

        AuthorityTime authorityTime = new AuthorityTime(mostBorrow,reserveTime,borrowTime);
        return  authorityTime;
    }
}

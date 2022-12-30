package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.SetTimeRequest;
import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.exception.InformationMissingException;
import fudan.se.lab2.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetTimeService {

    @Autowired
    AuthorityRepository authorityRepository;
    public boolean setTime(SetTimeRequest request, Authority authority){
        if(request.getNum() == 0 || request.getBorrowTime() == null || request.getReserveTime() == null ||request.getReserveTime().length!=4||request.getBorrowTime().length!=4){
            throw new InformationMissingException("Set time information ");
        }

        int[] reserveTime = request.getReserveTime();
        int[] borrowTime = request.getBorrowTime();

        long reserveOvertime = 0;
        long borrowOvertime = 0;
        reserveOvertime += (reserveTime[0]*24*60*60+reserveTime[1]*60*60+reserveTime[2]*60+reserveTime[3]);
        borrowOvertime += (borrowTime[0]*24*60*60+borrowTime[1]*60*60+borrowTime[2]*60+borrowTime[3]);

        authority.setMostBorrow(request.getNum());
        authority.setReserveOvertime(reserveOvertime);
        authority.setBorrowOvertime(borrowOvertime);

        authorityRepository.save(authority);
        return true;
    }
}

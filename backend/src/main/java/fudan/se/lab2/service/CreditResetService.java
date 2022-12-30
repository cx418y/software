package fudan.se.lab2.service;

import fudan.se.lab2.domain.CreditRecord;
import fudan.se.lab2.domain.Librarian;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.exception.UserNotFoundException;
import fudan.se.lab2.repository.CreditRecordRepository;
import fudan.se.lab2.repository.LibrarianRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CreditResetService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private CreditRecordRepository creditRecordRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");


    public String resetCredit(String userName, String adminName){
        User user = userRepository.findByUsername(userName);
        Librarian librarian = librarianRepository.findByUsername(adminName);
        if(user == null){
            throw new UserNotFoundException(userName);
        }
        int change = 100 - user.getCredit();
        Date date = new Date();
        String time = sdf.format(date);
        user.setCredit(100);
        CreditRecord creditRecord = new CreditRecord(user.getUsername(),adminName,change,"reset",time," ",user.getCredit());
        creditRecordRepository.save(creditRecord);
        userRepository.save(user);
        return "Credit refresh successfully!";
    }
}

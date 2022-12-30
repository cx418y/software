package fudan.se.lab2.service;

import fudan.se.lab2.Assist.MailUtil;
import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.MailBean;
import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.LibrarianRepository;
import fudan.se.lab2.repository.MailBeanRepository;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.controller.request.RegisterRequest;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author LBW
 */
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailBeanRepository mailBeanRepository;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    public AuthService(UserRepository userRepository, MailBeanRepository mailBeanRepository) {
        this.userRepository = userRepository;
        this.mailBeanRepository = mailBeanRepository;
    }

    public String register(RegisterRequest request) {
        // TODO: Implement the function.
        String username = request.getUserName();
        List<MailBean> mailBean = mailBeanRepository.findByUsername(username);
        int len = mailBean.size();
        int flag = 0;
        for(int i = 0; i < len; i++) {
            if (Integer.parseInt(request.getInput()) == mailBean.get(i).getCode()) {
                flag = 1;
                break;
            }
        }
        if(flag == 1){
            for(int i = 0; i < len; i++){
                mailBeanRepository.delete(mailBean.get(i));
            }

        }else{
            throw new BadCredentialsException("Verification is wrong!");
        }
            String authorityText = request.getAuthority();

            User newUser = new User(username, //username string
                    passwordEncoder.encode(request.getPassword()), //password string
                    request.getEmail(),
                    authorityService.getByAuthority(authorityText)//fullname string
                    );//authorities set<authorities>
            newUser.setCredit(100);
            userRepository.save(newUser);
            return "Register successfully!";


    }

    public User login(String username, String password) {
        // TODO: Implement the function.
        if(username == ""){
            throw new BadCredentialsException("username is null!");
        }
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User: '" + username + "' not found.");//defined by the frame
            //the same in jwtUserDetailsService
        }
        if(passwordEncoder.matches(password, user.getPassword())){//the order matters!!!!!


            return user;
        }else {
            throw new BadCredentialsException("Username or password is wrong!");
        }

    }

    public String email(String username) throws EmailException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            throw new UsernameHasBeenRegisteredException(username);
        }
        else{
            String recipient = username + "@fudan.edu.cn";
            MailBean mailBean = new MailBean();
            mailBean.setUsername(username);
            mailBean.setRecipient(recipient);
            mailBean.setSubject("Email Verify");
            Random r = new Random();
            int veriCode = r.nextInt(9000) + 1000;
            mailBean.setCode(veriCode);
            mailBean.setContent("User: " + username + "\n Your verify code is " + veriCode);
            mailBeanRepository.save(mailBean);
            MailUtil.sendSimpleTextEmail(mailBean);

            return "Send successfully!";
        }
    }




}

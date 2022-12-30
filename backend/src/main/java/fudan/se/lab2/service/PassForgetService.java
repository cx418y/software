package fudan.se.lab2.service;


import fudan.se.lab2.domain.MailBean;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.MailBeanRepository;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.Assist.MailUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class PassForgetService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailBeanRepository mailBeanRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Autowired
    public PassForgetService(UserRepository userRepository,  MailBeanRepository m) {
        this.userRepository = userRepository;
        this.mailBeanRepository = m;

    }

    public Map<String, String> forget(String username) throws EmailException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User: '" + username + "' not found.");
        }else {
            String recipient = username + "@fudan.edu.cn";
            MailBean mailBean = new MailBean();
            mailBean.setUsername(username);
            mailBean.setRecipient(recipient);
            mailBean.setSubject("Password Forget");
            Random r = new Random();
            int veriCode = r.nextInt(9000) + 1000;
            mailBean.setCode(veriCode);
            mailBean.setContent("User: " + username + "\n Your verify code is " + veriCode);
            mailBeanRepository.save(mailBean);
            MailUtil.sendSimpleTextEmail(mailBean);
            //mailBeanRepository.save(mailBean);

            //mailBean.

        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Send Successfully!");
        return response;

    }
    public Map<String, String> verify(String username, String input) {

        List<MailBean> mailBean = mailBeanRepository.findByUsername(username);
        int len = mailBean.size();
        int flag = 0;
        for(int i = 0; i < len; i++) {
            if (Integer.parseInt(input) == mailBean.get(i).getCode()) {
                flag = 1;
                break;
            }
        }

        if(flag == 1){
            for(int i = 0; i < len; i++){
                mailBeanRepository.delete(mailBean.get(i));
            }


            Map<String, String> response = new HashMap<>();
            response.put("message", "Verification Pass!");
            return response;
        }else{
            throw new BadCredentialsException("Verification is wrong!");
        }
    }

    public Map<String, String> reset(String username, String newPass) {
        User user = userRepository.findByUsername(username);
        user.setPassword(passwordEncoder.encode(newPass));// encode the password
        userRepository.save(user);//reset the password
        Map<String, String> response = new HashMap<>();
        response.put("message", "Reset password done!");
        return response;

    }

}

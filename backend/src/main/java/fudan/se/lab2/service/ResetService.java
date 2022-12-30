package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.ResetRequest;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResetService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public ResetService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String reset(ResetRequest request) {
        String username = request.getUsername();
        String oldPass = request.getOldPass();
        String newPass = request.getNewPass();
        User user = userRepository.findByUsername(username);
        if(passwordEncoder.matches(oldPass, user.getPassword())){//the order matters!!!!!
                user.setPassword(passwordEncoder.encode(newPass));// encode the password
                userRepository.save(user);//reset the password

                return "Reset password done!";

        }else {
            throw new BadCredentialsException("Password is wrong!");
        }

    }

}

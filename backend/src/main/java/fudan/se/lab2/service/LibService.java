package fudan.se.lab2.service;

import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.Librarian;
import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.LibrarianRepository;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.controller.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




/**
 * @author LBW
 */
@Service
public class LibService {
    private LibrarianRepository librarianRepository;
    //private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public LibService(LibrarianRepository librarianRepository) {
        //this.authorityRepository = authorityRepository;
        this.librarianRepository = librarianRepository;
    }


    public Librarian libLogin(String username, String password){
        if(username.equals("")){
            throw new BadCredentialsException("username is null!");
        }
        Librarian librarian = librarianRepository.findByUsername(username);
        if(librarian == null){
            throw new UsernameNotFoundException("User: '" + username + "' not found.");//defined by the frame
            //the same in jwtUserDetailsService
        }
        if(passwordEncoder.matches(password, librarian.getPassword())){//the order matters!!!!!

            return librarian;

        }else {
            throw new BadCredentialsException("Username or password is wrong!");
        }

    }

    public String libAdd(RegisterRequest request) {
        Librarian librarian = librarianRepository.findByUsername(request.getUserName());
        if(librarian != null){// find username registered
            throw new UsernameHasBeenRegisteredException(request.getUserName());
        }
        else{//didn't find username, register
            Librarian newLibrarian = new Librarian(request.getUserName(), //username string
                    passwordEncoder.encode(request.getPassword()), //password string
                    "librarian");//authorities set<authorities>
            librarianRepository.save(newLibrarian);
            return "Add successfully!";
        }

    }


}
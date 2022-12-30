package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.RegisterRequest;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService service;


    @Test
    void Login() {

        User user = service.login("19302010066","123456");

        assertNotNull(user);
        //   assertEquals("admin", librarian.getUsername());
        //  assertEquals("librarian", librarian.getAuthority());

    }

    @Test
    void loginWrongPass(){

        String s = "1";
        try{
            User user = service.login("19302010066","1234");
        }catch (BadCredentialsException e){
            s = e.getMessage();
        }finally {
            assertEquals("Username or password is wrong!", s);
        }
    }

    @Test
    void loginWrongName(){

        String s = "1";
        try{
            User user = service.login("19302010068","1234");
        }catch (UsernameNotFoundException e){
            s = e.getMessage();
        }finally {
            assertEquals("User: '19302010068' not found.", s);
        }
    }



    @Test
    void registerWrong() {
        try {
            RegisterRequest request = new RegisterRequest("19302010073", "123456", "19302010073@fudan。edu.cn", "student");
            String s = service.register(request);
            assertEquals("Register successfully!", s);
        }catch (Exception e){
            assertEquals("Verification is wrong!",e.getMessage());
        }
    }



    @Test
    void email() throws EmailException {
        assertEquals("Send successfully!",service.email("19302010067"));
    }

    @Test
    void emailFailed() throws EmailException {
        try {
            service.email("19302010084");
        }catch (Exception e){
            assertEquals("Username '19302010084' has been registered",e.getMessage());
        }
    }

}

package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.RegisterRequest;
import fudan.se.lab2.domain.Librarian;

import fudan.se.lab2.exception.UsernameHasBeenRegisteredException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class LibServiceTest {

    @Autowired
    private LibService service;
    @Test
    void libLogin() {

        Librarian librarian = service.libLogin("admin","password");

        assertNotNull(librarian);
         //   assertEquals("admin", librarian.getUsername());
          //  assertEquals("librarian", librarian.getAuthority());




    }

    @Test
    void libLoginWrongPass(){

        String s = "1";
        try{
            Librarian librarian = service.libLogin("admin","12345");
        }catch (BadCredentialsException e){
            s = e.getMessage();
        }finally {
            assertEquals("Username or password is wrong!", s);
        }
    }

    @Test
    void libLoginWrongName(){

        String s = "1";
        try{
            Librarian librarian = service.libLogin("adminn","password");
        }catch (UsernameNotFoundException e){
            s = e.getMessage();
        }finally {
            assertEquals("User: 'adminn' not found.", s);
        }
    }



    @Test
    void libAdd() {
        RegisterRequest request = new RegisterRequest("19302010073","123456", "lisha zhang", "librarian");
        String s = service.libAdd(request);
        assertEquals("Add successfully!", s);
    }



    @Test
    void libAddDuplicateName(){

        String s = "1";
        RegisterRequest request = new RegisterRequest("admin","123456", "lisha zhang", "librarian");
        try{
            s = service.libAdd(request);
        }catch (UsernameHasBeenRegisteredException e){
            s = e.getMessage();
        }finally {
            assertEquals("Username 'admin' has been registered", s);
        }
    }
}
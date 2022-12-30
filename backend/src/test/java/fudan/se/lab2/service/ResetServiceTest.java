package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.ResetRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

class ResetServiceTest {

    @Autowired
    private ResetService service;

    @Test
    void reset() {
        ResetRequest request = new ResetRequest("19302010066", "123456","newPass");
        String s = service.reset(request);
        assertEquals("Reset password done!", s);
    }

    @Test
    void resetWrongPass() {
        String s = "1";
        try {
            ResetRequest request = new ResetRequest("19302010066", "abc", "newPass");
            s = service.reset(request);
        }catch (BadCredentialsException e){
            s = e.getMessage();
        }finally {
            assertEquals("Password is wrong!", s);
        }
    }
}

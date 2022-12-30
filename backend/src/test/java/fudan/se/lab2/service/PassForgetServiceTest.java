package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.ForgetRequest;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PassForgetServiceTest {

    @Autowired
    private PassForgetService service;
    @Test
    void forget() throws EmailException {
        Map<String, String> response = service.forget("19302010066");
        assertEquals("Send Successfully!", response.get("message"));
    }


    @Test
    void reset() {
        Map<String, String> response = service.reset("19302010066", "password");
        assertEquals("Reset password done!", response.get("message"));
    }


    @Test
    void verifyWrong() {
        try{
            service.verify("19302010084","4256");
        }catch (Exception e){
            assertEquals("Verification is wrong!",e.getMessage());
        }
    }

}

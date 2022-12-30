package fudan.se.lab2.service;

import fudan.se.lab2.Assist.RemindRecord;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class RemindServiceTest {

    @Autowired
    RemindService remindService;

    @Test
    void remind() throws ParseException {
        List<RemindRecord> list = remindService.remind();
        assertNotNull(list);
    }


    /*
    @Test
    void sendRemind() throws EmailException {
        String[] username = {"19302010084"};
        assertEquals("send successfully!", remindService.sendRemind(username));
    }



     */
}

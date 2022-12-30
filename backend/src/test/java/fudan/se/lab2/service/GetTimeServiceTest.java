package fudan.se.lab2.service;

import fudan.se.lab2.Assist.AuthorityTime;
import fudan.se.lab2.domain.Authority;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class GetTimeServiceTest {

    @Autowired
    GetTimeService getTimeService;
    @Autowired
    AuthorityService authorityService;


    @Test
    void getTime() {
        Authority teacher = authorityService.getById(1);
        AuthorityTime authorityTime = getTimeService.getTime(teacher);
        assertEquals(9,authorityTime.getNum());
    }
}

package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.SetTimeRequest;
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
class SetTimeServiceTest {

    @Autowired
    AuthorityService authorityService;
    @Autowired
    SetTimeService setTimeService;

    @Test
    void setTime() {
        SetTimeRequest request = new SetTimeRequest(8, new int[]{0, 0, 0, 1}, new int[]{0, 0, 0, 0});
        setTimeService.setTime(request,authorityService.getById(1));
        assertEquals(authorityService.getById(1).getMostBorrow(),8);

    }
}

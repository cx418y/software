package fudan.se.lab2.service;

import fudan.se.lab2.Assist.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    void getUserInfo() {
        UserInfo userInfo = userInfoService.getUserInfo("19302010084");
        assertEquals(50,userInfo.getFine());
        assertEquals(100,userInfo.getCredit());
    }
}

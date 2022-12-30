package fudan.se.lab2.service;

import fudan.se.lab2.Assist.UserBookInfo;
import fudan.se.lab2.Assist.UserBorrowNotReturn;
import fudan.se.lab2.Assist.UserReserveNotFetch;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserBookInfoServiceTest {

    @Autowired
    private UserBookInfoService userBookInfoService;

    @Test
    void getUserReserveNotFetch() {
        List<UserReserveNotFetch> list = userBookInfoService.getUserReserveNotFetch("19302010084");
        assertEquals(list.get(0).getIsbn(),"1111111110-003");
    }

    @Test
    void getUserBorrowNotReturn() {
        List<UserBorrowNotReturn> list = userBookInfoService.getUserBorrowNotReturn("19302010084");
        assertEquals(list.get(0).getIsbn(),"1111111111-004");
    }
}

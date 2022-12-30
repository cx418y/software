package fudan.se.lab2.service;

import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.UserRepository;
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
class CreditResetServiceTest {

    @Autowired
    private CreditResetService creditResetService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void resetCredit() {
        creditResetService.resetCredit("19302010077","admin");
        User user = userRepository.findByUsername("19302010077");
        assertEquals(100, user.getCredit());
    }
}

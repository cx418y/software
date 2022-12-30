package fudan.se.lab2.service;

import fudan.se.lab2.domain.Area;
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
class AuthorityServiceTest {

    @Autowired
    AuthorityService authorityService;
    @Test
    void getById() {
        Authority a = authorityService.getById(1);
        assertEquals("Teacher",a.getAuthority());
    }

    @Test
    void getByAuthority() {
        Authority a = authorityService.getByAuthority("Teacher");
        assertEquals("Teacher",a.getAuthority());
    }
}

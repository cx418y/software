package fudan.se.lab2.service;

import fudan.se.lab2.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class JwtUserDetailsServiceTest {
    @Autowired
    private JwtUserDetailsService service;
    @Test
    void loadUserByUsername() {
        String s = "1";
        try{
           service.loadUserByUsername("19");
        }catch (UsernameNotFoundException  e){
            s = e.getMessage();
        }finally {
            assertEquals("User: '19' not found.", s);
        }
    }
}
package fudan.se.lab2.service;

import fudan.se.lab2.domain.Area;
import fudan.se.lab2.domain.BookState;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookStateServiceTest {

    @Autowired
    BookStateService bookStateService;

    @Test
    void get() {
        BookState a = bookStateService.get(0);
        assertEquals("available",a.getName());
    }
}

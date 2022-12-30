package fudan.se.lab2.service;

import fudan.se.lab2.domain.Category;
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
class CategoryServiceTest {

    @Autowired
    CategoryService categoryService;

    @Test
    void list() {
        List<Category> list = categoryService.list();
        assertEquals(1,list.get(0).getId());
        assertEquals("文学",list.get(0).getName());
    }

    @Test
    void get() {
    }
}

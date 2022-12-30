package fudan.se.lab2.service;

import fudan.se.lab2.domain.Area;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class AreaServiceTest {

    @Autowired
    AreaService areaService;

    @Test
    void getSuccessful() {
        Area a = areaService.get(1);
        assertEquals("handan",a.getName());
    }

    @Test
    void getUnsuccessful() {
        Area a = areaService.get(0);
        assertNull(a);
    }

    @Test
    void getByNameSuccessful() {
        Area a = areaService.getByName("handan");
        assertEquals(1,a.getId());
    }

    @Test
    void getByNameUnsuccessful() {
        Area a = areaService.getByName("han");
        assertNull(a);
    }


    @Test
    void getAreaSuccessful() {
        String[]s = areaService.getArea();
        String[]ss = {"handan","jiangwan","fenglin","zhangjiang"};
        assertEquals("handan",s[0]);
        assertEquals("jiangwan",s[1]);
        assertEquals("fenglin",s[2]);
        assertEquals("zhangjiang",s[3]);
    }

    @Test
    void getAid() {
        int aid = areaService.getAid("handan");
        assertEquals(1,aid);
    }
}

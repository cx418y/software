package fudan.se.lab2.service;

import fudan.se.lab2.Assist.AdminRecord;
import fudan.se.lab2.Assist.CopyRecord;
import fudan.se.lab2.Assist.UserFineRecord;
import fudan.se.lab2.Assist.UserRecord;
import fudan.se.lab2.domain.CreditRecord;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class RecordServiceTest {

    @Autowired
    RecordService recordService;
    @Test
    void getCopyRecord() {
        List<CopyRecord> list = recordService.getCopyRecord("1111111110-003");
        assertNotEquals(list.size(),0);
    }

    @Test
    void getCopyRecordBorrow() {
        List<CopyRecord> list = recordService.getCopyRecord("1111111110-004");
        assertNotEquals(list.size(),0);
    }

    @Test
    void getCopyRecordReturn() {
        List<CopyRecord> list = recordService.getCopyRecord("1111111110-001");
        assertNotEquals(list.size(),0);
    }
    @Test
    void getUserRecord() {
        List<UserRecord> list = recordService.getUserRecord("19302010084");
        assertNotNull(list);
    }

    @Test
    void getAdminRecords() {
        List<AdminRecord> list = recordService.getAdminRecords();
        assertNotNull(list);
    }

    @Test
    void getUserFineRecord() {
        List<UserFineRecord> list = recordService.getUserFineRecord("19302010084");
        assertNotNull(list);
    }

    @Test
    void getUserCreditRecord(){
        List<CreditRecord> list = recordService.creditRecords("19302010084");
        assertNotNull(list);
    }
}

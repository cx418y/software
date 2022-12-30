package fudan.se.lab2.controller;


import fudan.se.lab2.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    @Autowired
    RecordService recordService;

    //副本记录
    @GetMapping("api/copyRecord/{isbn}")
    public ResponseEntity<?> getCopyRecord(@PathVariable("isbn") String isbn) throws Exception{
        return ResponseEntity.ok(recordService.getCopyRecord(isbn));
    }

    @GetMapping("api/adminRecord")
    public ResponseEntity<?> getAdminRecord() throws Exception{
        return ResponseEntity.ok(recordService.getAdminRecords());
    }


    @GetMapping("api/userRecord/{userName}")
    public ResponseEntity<?> getUserRecord(@PathVariable("userName") String userName) throws Exception{
        return ResponseEntity.ok(recordService.getUserRecord(userName));
    }

    @GetMapping("api/userFineRecord/{userName}")
    public ResponseEntity<?> findCopies(@PathVariable("userName") String userName) throws Exception{
        return ResponseEntity.ok(recordService.getUserFineRecord(userName));
    }

    @GetMapping("api/creditRecord/{userName}")
    public ResponseEntity<?> CreditRecord(@PathVariable("userName") String userName) throws Exception{
        return ResponseEntity.ok(recordService.creditRecords(userName));
    }
}

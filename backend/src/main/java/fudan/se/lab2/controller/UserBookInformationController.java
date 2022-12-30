package fudan.se.lab2.controller;

import fudan.se.lab2.service.UserBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserBookInformationController {

    @Autowired
    private UserBookInfoService userBookInfoService;

    /*
    @GetMapping("api/information/{userName}")
    public ResponseEntity<?> returnBook(@PathVariable("userName") String userName) throws Exception {
        return ResponseEntity.ok(userBookInfoService.getUserInfo(userName));
    }

     */

    @GetMapping("api/information/getReserve/{userName}")
    public ResponseEntity<?> getReserve(@PathVariable("userName") String userName) throws Exception {
        return ResponseEntity.ok(userBookInfoService.getUserReserveNotFetch(userName));
    }

    @GetMapping("api/information/getReturn/{userName}")
    public ResponseEntity<?> getReturn(@PathVariable("userName") String userName) throws Exception {
        return ResponseEntity.ok(userBookInfoService.getUserBorrowNotReturn(userName));
    }

}

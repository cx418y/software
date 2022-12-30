package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.BorrowBookRequest;
import fudan.se.lab2.controller.request.ReturnBookRequest;
import fudan.se.lab2.service.ReverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ReversionController {
    @Autowired
    private ReverseService reverseService;
    @PostMapping("api/returnBook")
    public ResponseEntity<?> returnBook(@RequestBody ReturnBookRequest request) throws Exception {
        return ResponseEntity.ok(reverseService.reverse(request));
    }

}

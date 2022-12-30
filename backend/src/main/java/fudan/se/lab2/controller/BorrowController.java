package fudan.se.lab2.controller;
import fudan.se.lab2.controller.request.BorrowBookRequest;
import fudan.se.lab2.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping("api/borrowBook")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowBookRequest request) throws Exception {
        return ResponseEntity.ok(borrowService.borrow(request));
    }
}

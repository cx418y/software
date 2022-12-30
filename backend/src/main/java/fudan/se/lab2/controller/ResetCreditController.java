package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.BorrowBookRequest;
import fudan.se.lab2.controller.request.CreditRefreshRequest;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.service.CreditResetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResetCreditController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CreditResetService creditResetService;

    @GetMapping("/api/Adminrefresh")
    public  ResponseEntity<?> getAllUserCredit(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/api/Userrefresh")
    public  ResponseEntity<?> getUserCredit(@RequestParam(value = "username") String username){
        return ResponseEntity.ok(userRepository.findByUsername(username));
    }

    @PostMapping("/api/CreditRefresh")
    public  ResponseEntity<?> creditRefresh(@RequestBody CreditRefreshRequest request){
        return ResponseEntity.ok(creditResetService.resetCredit(request.getUsername(),request.getAdminName()));
    }

}

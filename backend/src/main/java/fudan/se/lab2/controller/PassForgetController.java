package fudan.se.lab2.controller;


import fudan.se.lab2.controller.request.ForgetRequest;
import fudan.se.lab2.service.PassForgetService;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class PassForgetController {

    private PassForgetService service;

    Logger logger = LoggerFactory.getLogger(ResetPassController.class);

    @Autowired
    public PassForgetController(PassForgetService service) {
        this.service = service;
    }


    @PostMapping("/api/forget")
    public ResponseEntity<?> forget(@RequestBody Map<String,String> user) throws EmailException {
        String username = user.get("username");
        logger.debug("ForgetForm: " + username);


        return new ResponseEntity(service.forget(username), HttpStatus.OK);
    }

    @PostMapping("/api/verify")
    public ResponseEntity<?> verify(@RequestBody ForgetRequest request) {
        String username = request.getUsername();
        String input = request.getInput();
        logger.debug("VerifyForm: " + username);
        return new ResponseEntity(service.verify(username, input), HttpStatus.OK);
    }

    @PostMapping("/api/forgetReset")
    public ResponseEntity<?> reset(@RequestBody ForgetRequest request) {
        String username = request.getUsername();
        String newPass = request.getInput();
        logger.debug("ResetForm: " + username);
        return new ResponseEntity(service.reset(username, newPass), HttpStatus.OK);
    }
}

package fudan.se.lab2.controller;
import fudan.se.lab2.domain.User;

import fudan.se.lab2.controller.request.ResetRequest;
import fudan.se.lab2.service.ResetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @author LBW
 */
@RestController
public class ResetPassController {


    private ResetService resetService;

    Logger logger = LoggerFactory.getLogger(ResetPassController.class);

    @Autowired
    public ResetPassController(ResetService resetService) {
        this.resetService = resetService;
    }


    @PostMapping("/api/reset")
    public ResponseEntity<?> reset(@RequestBody ResetRequest request, HttpSession session) {
        logger.debug("ResetForm: " + request.toString());
        request.setUsername(session.getAttribute("username").toString());
        return new ResponseEntity(resetService.reset(request), HttpStatus.OK);
    }
}

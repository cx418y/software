package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.RemindRequest;
import fudan.se.lab2.service.RemindService;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import fudan.se.lab2.security.jwt.JwtTokenUtil;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author LBW
 */
@RestController
public class RemindController {
    private JwtTokenUtil jwtTokenUtil;

    private RemindService service;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public RemindController(RemindService service) {
        this.service = service;
    }

    @GetMapping("/api/remind")
    public ResponseEntity<?> fineRemind() throws ParseException {
        logger.debug("Fine Remind");
        return new ResponseEntity(service.remind(), HttpStatus.OK);
    }



    @PostMapping("/api/sendRemind")
    public ResponseEntity<?> fineRemindShow(@RequestBody RemindRequest request) throws EmailException {
        logger.debug("Reverse Show");
        return new ResponseEntity(service.sendRemind(request.getUserNames()), HttpStatus.OK);
    }





}

package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.PayRequest;
import fudan.se.lab2.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author LBW
 */
@RestController
public class PayController {

    @Autowired
    private PayService service;

    Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    public PayController(PayService service) {
        this.service = service;
    }

    @PostMapping("/api/payFine")
    public ResponseEntity<?>Pay(@RequestBody PayRequest request) throws JSONException {
        logger.debug("PayForm:" + request.toString());
        return new ResponseEntity(service.pay(request), HttpStatus.OK);
    }

}


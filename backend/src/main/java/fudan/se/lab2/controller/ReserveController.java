package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.GetReserveRequest;
import fudan.se.lab2.controller.request.ReserveRequest;
import fudan.se.lab2.domain.Reserved;
import fudan.se.lab2.service.ReservedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ReserveController {
    @Autowired
    private ReservedService service;

    Logger logger = LoggerFactory.getLogger(LibController.class);

    @Autowired
    public ReserveController(ReservedService service) {
        this.service = service;
    }

    @PostMapping("/api/reserveBook")
    public ResponseEntity<?>reserve(@RequestBody ReserveRequest request){
        logger.debug("ReserveForm:" + request.toString());

        String response = service.reserve(request.getIsbn(), request.getUsername());
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/api/search-user/{keyword}")
    public ResponseEntity<?> searchUser(@PathVariable("keyword") String username ){
        logger.debug("ReserveSearchForm:" + username);
        List<Reserved> reservedList = service.reservedSearch(username);
        return new ResponseEntity(reservedList, HttpStatus.OK);
    }

    @PostMapping("api/getReserve")
    public ResponseEntity<?> fetchReserved(@RequestBody GetReserveRequest request)throws Exception{
        logger.debug("FetchReservedForm:" + request.toString());
        return new ResponseEntity(service.fetchReserved(request), HttpStatus.OK);
    }



}

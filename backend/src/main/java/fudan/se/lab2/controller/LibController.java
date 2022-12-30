package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.LibLoginRequest;
import fudan.se.lab2.controller.request.RegisterRequest;
import fudan.se.lab2.domain.Librarian;

import fudan.se.lab2.service.AreaService;


import fudan.se.lab2.service.LibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import fudan.se.lab2.security.jwt.JwtTokenUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author LBW
 */
@RestController
public class LibController {
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private LibService libService;
    @Autowired
    private AreaService areaService;


    Logger logger = LoggerFactory.getLogger(LibController.class);

    @Autowired
    public LibController(LibService libService) {
        this.libService = libService;
    }

    @PostMapping("/api/libLogin")
    public ResponseEntity<?>libLogin(@RequestBody LibLoginRequest request, HttpSession session ){
        logger.debug("LibLoginForm:" + request.toString());
        Librarian librarian = libService.libLogin(request.getUsername(), request.getPassword());
        session.setAttribute("username", librarian.getUsername());
        int aid = areaService.getAid(request.getArea());
        librarian.setAid(aid);
        return new ResponseEntity(librarian, HttpStatus.OK);
    }

    @PostMapping("/api/addLibrarian")
    public ResponseEntity<?>libAdd(@RequestBody RegisterRequest request){
            logger.debug("LibAddForm: " + request.toString());
            return new ResponseEntity(libService.libAdd(request), HttpStatus.OK);
    }
}


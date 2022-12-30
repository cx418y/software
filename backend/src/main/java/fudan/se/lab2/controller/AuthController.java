package fudan.se.lab2.controller;

import fudan.se.lab2.controller.request.ForgetRequest;

import fudan.se.lab2.domain.User;
import fudan.se.lab2.service.AuthService;

import fudan.se.lab2.controller.request.LoginRequest;
import fudan.se.lab2.controller.request.RegisterRequest;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import fudan.se.lab2.security.jwt.JwtTokenUtil;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LBW
 */
@RestController
public class AuthController {
    private JwtTokenUtil jwtTokenUtil;

    private AuthService authService;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/registerVerify")
    public ResponseEntity<?> email(@RequestBody Map<String,String> userName) throws EmailException {
        logger.debug("EmailForm: "+ userName.toString());
        return new ResponseEntity(authService.email(userName.get("userName")), HttpStatus.OK);
    }




    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        logger.debug("RegistrationForm: " + request.toString());
        return new ResponseEntity(authService.register(request), HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        logger.debug("LoginForm: " + request.toString());
        User user = authService.login(request.getUsername(), request.getPassword());
        session.setAttribute("username", user.getUsername());
        //session.setAttribute("");

        //System.out.println("登陆成功");
        //String token = jwtTokenUtil.generateToken(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    /**
     * This is a function to test your connectivity. (健康测试时，可能会用到它）.
     */
    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        Map<String, String> response = new HashMap<>();
        String message = "Welcome to 2021 Software Engineering Lab2. ";
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

}




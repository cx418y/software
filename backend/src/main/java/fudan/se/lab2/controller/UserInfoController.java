package fudan.se.lab2.controller;


import fudan.se.lab2.Assist.UserInfo;
import fudan.se.lab2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("api/userInfo/{userName}")
    public ResponseEntity<?> getReturn(@PathVariable("userName") String userName) throws Exception {
        return ResponseEntity.ok(userInfoService.getUserInfo(userName));
    }
}

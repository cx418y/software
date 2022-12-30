package fudan.se.lab2.controller;


import fudan.se.lab2.controller.request.ReturnBookRequest;
import fudan.se.lab2.controller.request.SetTimeRequest;
import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.service.AuthorityService;
import fudan.se.lab2.service.GetTimeService;
import fudan.se.lab2.service.SetTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetTimeController {
    @Autowired
    AuthorityService authorityService;
    @Autowired
    SetTimeService setTimeService;
    @Autowired
    GetTimeService getTimeService;

    @PostMapping("api/setTime/teacher")
    public ResponseEntity<?> SetTimeTeacher(@RequestBody SetTimeRequest request) throws Exception {
        Authority authority = authorityService.getByAuthority("Teacher");
        return ResponseEntity.ok(setTimeService.setTime(request,authority));
    }

    @PostMapping("api/setTime/graduate")
    public ResponseEntity<?> SetTimeGraduate(@RequestBody SetTimeRequest request) throws Exception {
        Authority authority = authorityService.getByAuthority("Graduate");
        return ResponseEntity.ok(setTimeService.setTime(request,authority));
    }

    @PostMapping("api/setTime/underGraduate")
    public ResponseEntity<?> returnBook(@RequestBody SetTimeRequest request) throws Exception {
        Authority authority = authorityService.getByAuthority("underGraduate");
        return ResponseEntity.ok(setTimeService.setTime(request,authority));
    }

    @GetMapping("api/getTime/teacher")
    public ResponseEntity<?> GetTimeTeacher() throws Exception {
        Authority authority = authorityService.getByAuthority("Teacher");
        return ResponseEntity.ok(getTimeService.getTime(authority));
    }

    @GetMapping("api/getTime/graduate")
    public ResponseEntity<?> GetTimeGraduate() throws Exception {
        Authority authority = authorityService.getByAuthority("Graduate");
        return ResponseEntity.ok(getTimeService.getTime(authority));
    }
    @GetMapping("api/getTime/underGraduate")
    public ResponseEntity<?> GetTimeUnderGraduate() throws Exception {
        Authority authority = authorityService.getByAuthority("underGraduate");
        return ResponseEntity.ok(getTimeService.getTime(authority));
    }
}

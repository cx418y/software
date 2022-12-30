package fudan.se.lab2.controller;


import fudan.se.lab2.controller.request.CommentRequest;
import fudan.se.lab2.controller.request.DeleteRequest;
import fudan.se.lab2.controller.request.ReplyRequest;
import fudan.se.lab2.domain.Reply;
import fudan.se.lab2.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



/**
 * @author LBW
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService service;

    Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/api/evaluation")
    public ResponseEntity<?>comment(@RequestBody CommentRequest request) throws JSONException {
        logger.debug("CommentForm:" + request.toString());
        return new ResponseEntity(service.comment(request), HttpStatus.OK);
    }

    @GetMapping("/api/readAllBooks/{userName}")
    public ResponseEntity<?>getRecord(@PathVariable("userName") String userName){
        logger.debug("CommentRecordForm" + userName);
        return new ResponseEntity(service.getRecord(userName), HttpStatus.OK);
    }

    @PostMapping("/api/deleteComment")
    public ResponseEntity<?>delete(@RequestBody DeleteRequest request){
        logger.debug("DeleteForm" + request.toString());
        return new ResponseEntity(service.delete(request), HttpStatus.OK);
    }

    @GetMapping("/api/getCommentList/{isbn}")
    public ResponseEntity<?>getCommentList(@PathVariable("isbn") String isbn){
        logger.debug("GETForm:" + isbn);
        return new ResponseEntity(service.getList(isbn), HttpStatus.OK);
    }

    @PostMapping("/api/commitReply")
    public ResponseEntity<?>reply(@RequestBody ReplyRequest request){
        logger.debug("ReplyForm:" + request.toString());
        return new ResponseEntity(service.reply(request), HttpStatus.OK);

    }





}

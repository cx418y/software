package fudan.se.lab2.service;


import fudan.se.lab2.controller.request.CommentRequest;
import fudan.se.lab2.controller.request.DeleteRequest;
import fudan.se.lab2.exception.BookNotFoundException;
import fudan.se.lab2.exception.CommentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired
    private CommentService service;

    @Test
    void commentSuccess() {
        //double rate, String evaluation, String isbn, String userName
        CommentRequest request = new CommentRequest(4.0, "okk","2015963854","19302010066");
        String str = service.comment(request);
        assertEquals("comment successfully!", str);
    }
    @Test
    void commentNoBook() {
        //double rate, String evaluation, String isbn, String userName
        String str;
        try{
        CommentRequest request = new CommentRequest(4.0, "okk","0000000000","19302010066");
        str = service.comment(request);
        }
        catch (BookNotFoundException e){
            str = e.getMessage();
            assertEquals("Book 0000000000 not found!", str);
        }

        //assertEquals("comment successfully!", str);
    }




    @Test
    void getRecordSuccess() {

    }

    @Test
    void deleteSuccess() {
        //String userName, int commentId
        DeleteRequest request = new DeleteRequest("19302010084", 1);
        String str = service.delete(request);
        assertEquals("delete successfully!", str);
    }

    void deleteFail() {
        String str;
        try{
        //String userName, int commentId
            DeleteRequest request = new DeleteRequest("19302010066", 0);
            str = service.delete(request);
        }
        catch (CommentNotFoundException e){
            str = e.getMessage();
            assertEquals("comment is not found", str);
        }

        //assertEquals("delete successfully!", str);
    }

    @Test
    void reply() {
    }

    @Test
    void getList() {
    }
}

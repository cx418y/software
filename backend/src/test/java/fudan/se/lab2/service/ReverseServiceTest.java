package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.ReturnBookRequest;
import fudan.se.lab2.domain.Book;
import fudan.se.lab2.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ReverseServiceTest {

    @Autowired
    ReverseService reverseService;
    @Autowired
    BookRepository bookRepository;

    @Test
    void reverseSuccessful(){
        try {
            //归还四本分别为正常、超时、损坏、遗失
            String[] isbns = {"1111111111-001", "1111111111-002", "1111111111-003", "1111111111-004"};
            Book book1 = bookRepository.findBookByIsbn("1111111111-001");
            Book book2 = bookRepository.findBookByIsbn("1111111111-002");
            Book book3 = bookRepository.findBookByIsbn("1111111111-003");
            Book book4 = bookRepository.findBookByIsbn("1111111111-004");

            ReturnBookRequest request = new ReturnBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setReturnAid(1);
            request.setStates(new String[]{"good", "good", "damaged", "missing"});
            assertTrue(reverseService.reverse(request));
            //更新图书状态
            assertEquals("available", book1.getBookState().getName());
            assertEquals("available", book2.getBookState().getName());
            assertEquals("damaged", book3.getBookState().getName());
            assertEquals("missing", book4.getBookState().getName());
        }catch(Exception e){
            assertEquals("book 1111111111-002 borrowed overtime, fine 12.5 yuan! user 19302010066 credit reduce 20, current credit is: 80\n" +
                    "book 1111111111-003 is damaged, fine 25.0 yuan! user 19302010066 credit reduce 30, current credit is: 50\n" +
                    "book 1111111111-004 is missing, fine 50.0 yuan! user 19302010084 credit reduce 40, current credit is: 60\n",e.getMessage());
        }
    }

    @Test
    void reverseIsbnError() {
     //   Book book = bookRepository.findBookByIsbn(1111111111-001");
        try {
            String[]isbn = {"xxx"};
            String[]states = {"normal"};
            ReturnBookRequest request = new ReturnBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbn);
            request.setStates(states);
            request.setReturnAid(1);
            assertTrue(reverseService.reverse(request));
        }catch (Exception e){
            assertEquals("Book xxx not found!",e.getMessage());
        }
    }

    @Test
    void reverseNotBorrowedBook() {
        Book book = bookRepository.findBookByIsbn("1111111111-005");
        String[]isbns = {"1111111111-005"};
        String[]states = {"normal"};
        try {
            ReturnBookRequest request = new ReturnBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setStates(states);
            request.setReturnAid(1);
            assertTrue(reverseService.reverse(request));
        }catch (Exception e){
            assertEquals("Book has not been borrowed, it is "+ book.getBookState().getName()+"!",e.getMessage());
        }
    }

    @Test
    void reverseInfoMissing() {
        try {
            String[]isbns = {"1111111111-004"};
            String[]states = {"normal"};
            ReturnBookRequest request = new ReturnBookRequest();
            request.setUserName("");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setStates(states);
            request.setReturnAid(1);
            assertTrue(reverseService.reverse(request));
        }catch (Exception e){
            assertEquals("return information missing!",e.getMessage());
        }
    }

    @Test
    void reverseAreaMissing() {
        try {
            String[]isbns = {"1111111111-001"};
            String[]states = {"normal"};
            ReturnBookRequest request = new ReturnBookRequest();
            request.setUserName("");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setStates(states);
            request.setReturnAid(1);
          //  assertTrue(reverseService.reverse(request));
        }catch (Exception e){
            assertEquals("return information missing!",e.getMessage());
        }
    }

    @Test
    void reverseUserNouFound() {
        try {
            String[]isbns = {"1111111111-001"};
            String[]states = {"normal"};
            ReturnBookRequest request = new ReturnBookRequest();
            request.setUserName("xxx");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setStates(states);
            request.setReturnAid(1);
        }catch (Exception e){
            assertEquals("User xxx not found!",e.getMessage());
        }
    }

    @Test
    void reverseLibHasNoAuthority() {
        try {
            String[]isbns = {"1111111111-001"};
            String[]states = {"normal"};
            ReturnBookRequest request = new ReturnBookRequest();
            request.setUserName("admin");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setStates(states);
            request.setReturnAid(1);
        }catch (Exception e){
            assertEquals("Don't have return authority!",e.getMessage());
        }
    }



}

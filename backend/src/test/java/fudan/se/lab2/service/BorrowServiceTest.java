package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.BorrowBookRequest;
import fudan.se.lab2.domain.Book;
import fudan.se.lab2.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class BorrowServiceTest {

    @Autowired
    BorrowService borrowService;
    @Autowired
    BookRepository bookRepository;

    @Test
    void borrowSuccessful() {
        Book book = bookRepository.findBookByIsbn("7898966523-002");
        assertEquals(0,book.getBookState().getId());

        String[] isbns = {"7898966523-002"};
        BorrowBookRequest request = new BorrowBookRequest();
        request.setUserName("19302010084");
        request.setAdminName("admin");
        request.setIsbns(isbns);
        request.setAid(book.getArea().getId());
        assertTrue(borrowService.borrow(request));

        //更新图书状态
        assertEquals(2,book.getBookState().getId());

    }

    @Test
    void borrowCreditNotEnough(){
        try{
            Book book = bookRepository.findBookByIsbn("7898966523-002");
            assertEquals(0,book.getBookState().getId());

            String[] isbns = {"7898966523-002"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010077");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(book.getArea().getId());
            borrowService.borrow(request);

        }catch (Exception e){
            assertEquals("Credit not enough,current credit is :-10",e.getMessage());
        }
    }

    @Test
    void borrowNotFound() {
        try {
         //   String[] isbns = {"7898966523-002"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(new String[]{"xxx"});
            request.setAid(1);
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("Book xxx not found!",e.getMessage());
        }
    }


    @Test
    void borrowInfoMissing() {
        try {
            String[] isbns = {"7898966523-002"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("");
            request.setIsbns(isbns);
            request.setAid(1);
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("information missing!",e.getMessage());
        }
    }

    @Test
    void borrowAreNotMatching() {
        try {
            String[] isbns = {"1111111111-005"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(2);
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("11111111111-005 Area not matching!",e.getMessage());
        }
    }

    @Test
    void borrowUserNotFound() {
        try {
            String[] isbns = {"7898966523-002"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("xxx");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(1);
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("xxx not found!",e.getMessage());
        }
    }

    @Test
    void borrowLibHaveNoAuthority() {
        try {
            String[] isbns = {"7898966523-002"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("admin");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(1);
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("Don't have borrow authority!",e.getMessage());
        }
    }

    @Test
    void borrowReserved() {
        try {
            //已被预约的书
            Book book = bookRepository.findBookByIsbn("1111111110-003");
            assertEquals(1,book.getBookState().getId());

            String[] isbns = {"1111111110-003"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(book.getArea().getId());
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("Book has been reserved by others!",e.getMessage());
        }
    }

    @Test
    void borrowBorrowed() {
        try {
            //已被借阅的书
            Book book = bookRepository.findBookByIsbn("1111111111-004");
            assertEquals(2,book.getBookState().getId());
            String[] isbns = {"1111111111-004"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(book.getArea().getId());
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("Book has been borrowed by others!",e.getMessage());
        }
    }

    @Test
    void borrowMostNum() {
        try {
            String[] isbns = {"1111111111-006","1111111111-007"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(1);
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("user 19302010084 can borrow 1 books most!",e.getMessage());
        }
    }


    @Test
    void borrowDameged() {
        try {
            //已被预约的书
            Book book = bookRepository.findBookByIsbn("1111111110-002");
            assertEquals(3,book.getBookState().getId());
            String[] isbns = {"1111111110-002"};
            BorrowBookRequest request = new BorrowBookRequest();
            request.setUserName("19302010084");
            request.setAdminName("admin");
            request.setIsbns(isbns);
            request.setAid(book.getArea().getId());
            borrowService.borrow(request);
        }catch (Exception e){
            assertEquals("Book has damaged!",e.getMessage());
        }
    }




}

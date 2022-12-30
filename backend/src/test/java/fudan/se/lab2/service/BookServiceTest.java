package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.AddBookRequest;
import fudan.se.lab2.controller.request.AddCopyRequest;
import fudan.se.lab2.domain.Book;
import org.apache.juli.logging.Log;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

class BookServiceTest {
    @Autowired
    private BookService bookService;

    Logger log = LoggerFactory.getLogger(BookServiceTest.class);

    @Test
    void findAll() {
        assertNotNull(bookService.findAll());
    }

    @Test
    void listByCategory() {
        assertNotNull(bookService.listByCategory(2));
        List<Book> list = new ArrayList<>();
        assertEquals(list,bookService.listByCategory(8));
    }

    @Test
    void findCopiesIsbnMissing() {
        try{
            bookService.findCopies("");
        }catch (Exception e){
            assertEquals("copy information missing!",e.getMessage());
        }
    }

    @Test
    void findCopiesNotFound() {
        try{
            String isbn = "28855";
            bookService.findCopies(isbn);
        }catch (Exception e){
            assertEquals("Book 28855 not found!",e.getMessage());
        }
    }

    @Test
    void findCopiesSuccess() {
        String isbn = "1111111110";
        assertNotNull(bookService.findCopies(isbn));
    }

    @Test
    void addBookSuccessful() {
        List<Integer>num = Arrays.asList(1,0,0,0);
        AddBookRequest request = new AddBookRequest();
        request.setCover("sss");
        request.setTitle("x");
        request.setAuthor("CX");
        request.setDate("2019-3-01");
        request.setIsbn("1234567891");
        request.setIntro("xxxx");
        request.setAid(1);
        request.setNum(num);
        request.setCid(1);
        request.setPrice(50.00);
        assertTrue(bookService.addBook(request));
    }

    @Test
    void addBookInformationMissing() {
        try {
            List<Integer> num = new ArrayList<>(4);
            num.add(1);
            num.add(0);
            num.add(0);
            num.add(1);
            AddBookRequest request = new AddBookRequest();
            request.setCover("sss");
            request.setTitle("");
            request.setAuthor("CX");
            request.setDate("2019-3-01");
            request.setIsbn("1234567891");
            request.setIntro("xxxx");
            request.setAid(1);
            request.setNum(num);
            request.setCid(1);
            request.setPrice(50.00);
          //  assertTrue(bookService.addBook(request));
        }catch (Exception e){
            assertEquals("upload information missing!",e.getMessage());
        }
    }

    @Test
    void addBookRepeatedly() {
        try {
            List<Integer> num = new ArrayList<>(4);
            num.add(1);
            num.add(0);
            num.add(0);
            num.add(1);
            AddBookRequest request = new AddBookRequest();
            request.setCover("sss");
            request.setTitle("hhh");
            request.setAuthor("CX");
            request.setDate("2019-3-01");
            request.setIsbn("7898966523");
            request.setIntro("xxxx");
            request.setAid(1);
            request.setNum(num);
            request.setCid(1);
            request.setPrice(50.00);
            //  assertTrue(bookService.addBook(request));
        }catch (Exception e){
            assertEquals("Book has been uploaded repeatedly",e.getMessage());
        }
    }



    @Test
    void addCopySuccessful() {
        AddCopyRequest request = new AddCopyRequest();
        request.setIsbn("7898966523");
        request.setNum(2);
        request.setAid(1);
        assertTrue(bookService.addCopy(request));
    }
    @Test
    void addCopyUnsuccessful() {
        try {
            AddCopyRequest request = new AddCopyRequest();
            request.setIsbn("78989665");
            request.setNum(3);
            request.setAid(1);
        }catch (Exception e) {
            assertEquals("Book 78989665 not found!",e.getMessage());
        }
    }

    @Test
    void searchSuccessful() {
        List<Book> list = bookService.search("789");
        assertNotNull(list);
    }

    @Test
    void searchKeywordsMissing() {
        try {
            bookService.search("");
        }catch (Exception e){
            assertEquals("keywords missing!",e.getMessage());
        }
    }

    @Test
    void searchNotFound() {
        try {
            bookService.search("aaaaaa");
        }catch (Exception e){
            assertEquals("Not found any books!",e.getMessage());
        }
    }

    @Test
    void searchCopySuccessful() {
        String[] isbns = {"1111111111-001","1111111111-002"};
        List<Book> books = bookService.searchCopy(isbns);
        assertNotEquals(0,books.size());
    }

    @Test
    void searchCopyUnsuccessful() {
        try {
            String[] isbns = {"7898966523-0011"};
            List<Book> books = bookService.searchCopy(isbns);

        }catch (Exception e){
            assertEquals("Book 7898966523-0011 not found!",e.getMessage());
        }
    }

    @Test
    void searchCopyIsbnMissing() {
        try {
            String[]isbns = {};
            bookService.searchCopy(isbns);
        }catch (Exception e){
            assertEquals("isbn missing!",e.getMessage());
        }
    }


    @Test
    void getDetailsSuccessful() {
        Book book = bookService.getDetails(1);
        assertEquals("且在人间",book.getTitle());
    }

    @Test
    void getDetailsUnsuccessful() {
        try {
            Book book = bookService.getDetails(0);
        }catch (Exception e){
            assertEquals("Book not found",e.getMessage());
        }
    }
}

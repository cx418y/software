package fudan.se.lab2.service;

import fudan.se.lab2.controller.request.GetReserveRequest;
import fudan.se.lab2.controller.request.ResetRequest;
import fudan.se.lab2.domain.Reserved;
import fudan.se.lab2.exception.BookNotAvailableException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ReservedServiceTest {
    @Autowired
    private ReservedService service;

    @Test
    void reserve() {
        String s = service.reserve("7892596523-002", "19302010066");
        assertEquals("Reserved Successfully!", s);
    }

    @Test
    void reserverCreditNotEnough(){
        try{
            service.reserve("7892596523-002", "19302010077");
        }catch (Exception e){
            assertEquals("Credit not enough,current credit is: -10",e.getMessage());
        }
    }

    @Test
    void reserveNoBook(){
        try{
            service.reserve("1111111110-002","19302010066");
        }catch (BookNotAvailableException e) {
            assertEquals("ISBN '1111111110-002' isn't available", e.getMessage());
            //   s = e.getMessage();
        }
    }


    @Test
    void reservedSearch() {
        List<Reserved> r = service.reservedSearch("19302010084");
        assertNotNull(r);
    }

    @Test
    void reservedSearchNull() {
        String s = "1";
        List<Reserved> r = null;
        try{
            r = service.reservedSearch("19302010066");
        }catch (Exception e){
            s = e.getMessage();
            assertEquals("No reservations!", e.getMessage());
        }finally {
            assertEquals("[]",r.toString());
        }

    }

    @Test
    void fetchReserved() {
        try {
            String[] isbn = {"1111111110-003"};
            GetReserveRequest request = new GetReserveRequest("19302010084", 1, "admin", isbn);
            String s = service.fetchReserved(request);
            assertEquals("Fetch Successfully!", s);
        }catch (Exception e){

        }
    }

    @Test
    void fetchReservedNoRecord() {
        String s = "1";
        try{
            String[] isbn = {"789"};
            GetReserveRequest request = new GetReserveRequest("19302010066", 0, "admin",isbn);
            s = service.fetchReserved(request);
        }catch (BadCredentialsException | ParseException e){
            s = e.getMessage();
        }finally {
            assertEquals("No reservation record!", s);
        }
    }

    @Test
    void fetchReservedOvertime()  {
        try {
            String[] isbn = {"1111111111-008"};
            GetReserveRequest request = new GetReserveRequest("19302010084", 1, "admin", isbn);
            service.fetchReserved(request);
        }catch (Exception e){
            assertEquals(e.getMessage(),"1111111111-008  reservation are expired!");
        }
    }

    @Test
    void fetchReservedAreaNotMatching()  {
        try {
            String[] isbn = {"1111111110-003"};
            GetReserveRequest request = new GetReserveRequest("19302010084", 2, "admin", isbn);
            service.fetchReserved(request);
        }catch (Exception e){
            assertEquals(e.getMessage(),"1111111110-003  area not matching");
        }
    }




}

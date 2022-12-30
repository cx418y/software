package fudan.se.lab2.controller;
import fudan.se.lab2.Assist.StringUtils;
import fudan.se.lab2.controller.request.AddBookRequest;
import fudan.se.lab2.controller.request.AddCopyRequest;
import fudan.se.lab2.service.AreaService;
import fudan.se.lab2.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author LBW
 */
@CrossOrigin
@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AreaService areaService;

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/api/findAllBooks")
    public  ResponseEntity<?> find(){
        return ResponseEntity.ok(bookService.findAll());
    }

    //图书上新
    @PostMapping("/api/addBook")
    public ResponseEntity<?> addBook(@RequestBody AddBookRequest request) throws Exception {
        return ResponseEntity.ok(bookService.addBook(request));
    }

    //图书分类展示
    @GetMapping("/api/categories/{cid}/books")
    public ResponseEntity<?> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return ResponseEntity.ok(bookService.listByCategory(cid));
        } else {
            return find();
        }
    }

    //图书封面上传
    @CrossOrigin
    @PostMapping(value="api/covers",produces = "application/json")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "/home/img";
        String s = file.getOriginalFilename().substring(file.getOriginalFilename().length()-4);
        String fileName = StringUtils.getRandomString(6,s);
        File f = new File(folder,fileName);
        while(f.exists()){
            fileName = StringUtils.getRandomString(6,s);
            f = new File(folder,fileName);
        }
        try {
            file.transferTo(f);
            String imgURL = "http://106.15.194.40:8080/api/file/" + f.getName();
            return imgURL;
        } catch (Exception e) {
            return "";
        }
    }

    //副本增加
    @PostMapping("api/addCopies")
    public ResponseEntity<?> addCopies(@RequestBody AddCopyRequest request) throws Exception {
        return ResponseEntity.ok(bookService.addCopy(request));
    }


    @GetMapping("api/getArea")
    public ResponseEntity<?> getArea()throws Exception{
        return ResponseEntity.ok(areaService.getArea());
    }

    //副本详情
    @GetMapping("api/getCopies/{isbn}")
    public ResponseEntity<?> findCopies(@PathVariable("isbn") String isbn) throws Exception{
        return ResponseEntity.ok(bookService.findCopies(isbn));
    }

    @GetMapping("api/search")
    public ResponseEntity<?> search(@RequestParam(value = "keywords") String keywords) throws Exception{
        return ResponseEntity.ok(bookService.search(keywords));
    }

    @GetMapping("api/searchCopy")
    public ResponseEntity<?> searchCopy(@RequestParam(value = "keywords") String isbn) throws Exception{
        String [] isbns = isbn.split(",");
        return ResponseEntity.ok(bookService.searchCopy(isbns));
    }

    @GetMapping("api/getDetails/{id}")
    public ResponseEntity<?> getDetails(@PathVariable("id") int id ) throws Exception{
        return ResponseEntity.ok(bookService.getDetails(id));
    }



}

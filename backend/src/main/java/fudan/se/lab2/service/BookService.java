package fudan.se.lab2.service;

import fudan.se.lab2.Assist.BookCopies;
import fudan.se.lab2.controller.request.AddBookRequest;
import fudan.se.lab2.controller.request.AddCopyRequest;
import fudan.se.lab2.domain.*;

import fudan.se.lab2.exception.InformationMissingException;
import fudan.se.lab2.exception.BookNotFoundException;
import fudan.se.lab2.exception.BookRepeatedUploadException;
import fudan.se.lab2.repository.BookRepository;
import fudan.se.lab2.repository.BorrowRepository;

import fudan.se.lab2.repository.ReservedRepository;
import fudan.se.lab2.repository.ReversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.awt.print.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LBW
 */

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private BookStateService bookStateService;
    @Autowired
    private ReservedRepository reservedRepository;
    @Autowired
    private ReversionRepository reversionRepository;


    @Autowired
    public BookService() {
    }

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //查找全部正本
    public List<Book> findAll() {
        //Sort sort = Sort.by(Sort.Direction.DESC,"id");
        List<Book> list = (List<Book>) bookRepository.findAllByIsCopy(0);
        return list;
    }

    //通过类别查找
    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookRepository.findAllByCategoryAndIsCopy(category, 0);
    }

    //查找副本
    public List<BookCopies> findCopies(String isbn){
        if(isbn == ""){
            throw new InformationMissingException("copy information");
        }
        StringBuilder stringBuilder = new StringBuilder(isbn);
        String isbn1 = stringBuilder.append("-").toString();
        List<Book> list = bookRepository.findAllByIsbnContains(isbn1);
        List<BookCopies> copies = new ArrayList<>();
        if(list.size()==0){
            throw new BookNotFoundException("Book "+isbn+" not found!");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                Book book = list.get(i);
                if (book.getBookState().getId() == 0) {
                    BookCopies copy = new BookCopies(book.getIsbn(), book.getBookState(), book.getArea().getName(), " "," ");
                    copies.add(copy);
                }
                //预约
                else if (book.getBookState().getId() == 1) {
                    Reserved reservedBook = reservedRepository.findByBookAndBorrowIs(book,0);
                    if (reservedBook != null) {
                        BookCopies copy = new BookCopies(book.getIsbn(), book.getBookState(), book.getArea().getName(), reservedBook.getUsername()," ");
                        copies.add(copy);
                    }
                }
                //借出
                else if (book.getBookState().getId() == 2) {
                    Borrow borrowBook = borrowRepository.findBorrowByBookAndReverseIs(book,0);
                    if (borrowBook != null) {
                        BookCopies copy = new BookCopies(book.getIsbn(), book.getBookState(), "", borrowBook.getUserName(),borrowBook.getAdminName());
                        copies.add(copy);
                    }
                }
                else if (book.getBookState().getId() == 3) {
                    Reversion reversion = reversionRepository.findByBookAndState(book,"damaged");
                    BookCopies copy = new BookCopies(book.getIsbn(), book.getBookState(), "", "",reversion.getReturnAdmin());
                    copies.add(copy);
                }
                //遗失
                else {
                    Reversion reversion = reversionRepository.findByBookAndState(book,"missing");
                    BookCopies copy = new BookCopies(book.getIsbn(), book.getBookState(), "", "",reversion.getReturnAdmin());
                    copies.add(copy);
                }
            }
        }
        return copies;
    }

    //图书上新
    public boolean addBook(AddBookRequest request) {
        if (request.getCover() != "" && request.getTitle() != "" && request.getAuthor() != "" && request.getDate() != "" && request.getIsbn() != "" && request.getIntro() != ""&&request.getPrice() > 0) {
            //图书上新
            if (bookRepository.findBookByIsbn(request.getIsbn()) == null) {
                //新建图书对象
                Book bookNew = new Book(request.getCover(), request.getTitle(), request.getAuthor(), request.getDate(), request.getIntro(),request.getPrice());
                bookNew.setIsbn(request.getIsbn());  //isbn
                Category category = categoryService.get(request.getCid()); //图书类别
                bookNew.setCategory(category);
                Area area = areaService.get(request.getAid());  //图书所在校区
                bookNew.setArea(area);
                BookState bookState = bookStateService.get(0);  //图书状态默认available
                bookNew.setBookState(bookState);

                bookNew.setNumber(0);   //初始化副本数量为零
                bookNew.setIsCopy(0);
                bookNew.setRate(0);
                bookNew.setCommentNumber(0);
                  //存入数据库
                bookRepository.save(bookNew);


                Book book = bookRepository.findBookByIsbn(request.getIsbn());
                //副本添加
                StringBuilder isbn = new StringBuilder(request.getIsbn()+"-");
                for (int i = 0; i < request.getNum().size(); i++) {
                    for (int j = 0; j < request.getNum().get(i); j++) {
                        Book bookCopy = new Book(request.getCover(), request.getTitle(), request.getAuthor(), request.getDate(), request.getIntro(),request.getPrice());
                        Category category1 = categoryService.get(request.getCid()); //图书类别
                        bookCopy.setCategory(category1);

                        Area area1 = areaService.get(i + 1);  //图书所在校区
                        bookCopy.setArea(area1);

                        BookState bookState1 = bookStateService.get(0);  //图书状态默认available
                        bookCopy.setBookState(bookState1);

                        bookCopy.setIsCopy(1);  //副本标识

                        //副本标识号
                        if(book.getNumber()<9){
                            bookCopy.setIsbn((isbn+"00"+(book.getNumber()+1)).toString());
                        }else if(book.getNumber()<99){
                            bookCopy.setIsbn((isbn+"0"+(book.getNumber()+1)).toString());
                        }else{
                            bookCopy.setIsbn((isbn+""+(book.getNumber()+1)).toString());
                        }
                        book.setNumber(book.getNumber()+1);  //更新副本数量

                        bookRepository.save(bookCopy);    //存入数据库
                        bookRepository.save(book);

                    }
                }

                return true;
            } else {
                throw new BookRepeatedUploadException();
               // return false;
            }
        } else {
            throw new InformationMissingException("upload information");
           // return false;
        }
    }

    //副本上传
    public boolean addCopy(AddCopyRequest request){
        StringBuilder isbn = new StringBuilder(request.getIsbn()+"-");
        Book book = bookRepository.findBookByIsbn(request.getIsbn());
        if(book==null){ throw new BookNotFoundException("Book " +request.getIsbn()+" not found!");
        }
        for (int i = 0; i < request.getNum();i++) {
                Book bookCopy = new Book(book.getCover(), book.getTitle(), book.getAuthor(), book.getDate(), book.getIntro(),book.getPrice());
                Category category = categoryService.get(book.getCategory().getId()); //图书类别
                bookCopy.setCategory(category);

                Area area = areaService.get(request.getAid());  //图书所在校区
                bookCopy.setArea(area);

                BookState bookState = bookStateService.get(0);  //图书状态默认available
                bookCopy.setBookState(bookState);

                bookCopy.setIsCopy(1);  //副本标识

                //副本标识号
                if(book.getNumber()<9){
                    bookCopy.setIsbn((isbn+"00"+(book.getNumber()+1)).toString());
                }else if(book.getNumber()<99){
                    bookCopy.setIsbn((isbn+"0"+(book.getNumber()+1)).toString());
                }else{
                    bookCopy.setIsbn((isbn+""+(book.getNumber()+1)).toString());
                }
                book.setNumber(book.getNumber()+1);  //更新副本数量
                bookRepository.save(book);
                bookRepository.save(bookCopy);    //存入数据库

        }
        return true;
    }

    //模糊搜索
    public List<Book> search(String keywords){
        if(keywords==""){
            throw new InformationMissingException("keywords");
        }
        List<Book> list= bookRepository.findAllByTitleLikeAndIsCopyOrAuthorLikeAndIsCopyOrIsbnLikeAndIsCopy('%'+keywords+'%',0,'%'+keywords+'%',0,'%'+keywords+'%',0);
        if(list == null){
            throw new BookNotFoundException("Not found any books!");
        }else{
            return list;
        }
    }

    //查找副本
    public List<Book> searchCopy(String[] isbns){
        if(isbns.length==0){
            throw new InformationMissingException("isbn");
        }
        List<Book> lists = new ArrayList<>();
        for(int i = 0; i < isbns.length; i++) {
            Book book = bookRepository.findBookByIsbnAndIsCopy(isbns[i], 1);
            if (book == null) {
                throw new BookNotFoundException("Book " + isbns[i] + " not found!");
            }else {
                lists.add(book);
            }
        }
        return lists;
    }

    public Book getDetails(int id){
        Book book = bookRepository.findBookById(id);
        if(book == null){
            throw new BookNotFoundException("Book not found");
        }else return book;
    }


}

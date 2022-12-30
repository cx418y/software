package fudan.se.lab2.repository;


import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.Category;

import org.springframework.data.repository.CrudRepository;


//import java.awt.print.Book;
import java.util.List;

/**
 * @author LBW
 */

public interface BookRepository extends CrudRepository<Book,Integer> {
    List<Book> findAllByAuthor(String author);

    List<Book> findAllByIsbn(String isbn);

    List<Book> findAllByCategoryAndIsCopy(Category category, int isCopy);

    List<Book> findAllByIsCopy(int isCopy);

    Book findBookByIsbn(String isbn);

    List<Book> findAllByIsbnContains(String s);

    Book findBookById(int id);

    List<Book> findAllByTitleLikeAndIsCopyOrAuthorLikeAndIsCopyOrIsbnLikeAndIsCopy(String keywords1,int isCopy1,String keywords2,int isCopy2,String keywords3,int isCopy3);

    List<Book> findAllByCategory(Category category);
    Book findByIsbn(String isbn);

    Book findBookByIsbnAndIsCopy(String isbn,int isCopy);

    //List<Book> findAllByTitle(String title);
}


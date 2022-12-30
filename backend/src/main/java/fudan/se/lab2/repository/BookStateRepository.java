package fudan.se.lab2.repository;
import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.BookState;
import fudan.se.lab2.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.awt.print.Book;
import java.util.List;

public interface BookStateRepository extends CrudRepository<BookState,Integer>{

}

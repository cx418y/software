package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.Reversion;
import javassist.runtime.Desc;
import org.hibernate.criterion.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReversionRepository extends CrudRepository<Reversion,Integer> {

    List<Reversion> findAllByBorrowUsername(String BroUserName);

    List<Reversion> findAllByReturnUsername(String ReUserName);

    List<Reversion> findAllByBook(Book book);

    List<Reversion> findAllByReturnAdmin(String returnAdmin);

    Reversion findByBookAndState(Book book,String state);

    List<Reversion> findAllByBorrowUsernameAndOvertime(String borrowUsername, int overtime);


}

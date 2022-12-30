package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.Reserved;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedRepository extends CrudRepository<Reserved, Long> {
    List<Reserved> findAllByUsername(String username);

    List<Reserved> findAllByUsernameAndOvertimeAndBorrow(String username, int overtime,int borrow);

    Reserved findByBook(Book book);
    List<Reserved> findAllByBook(Book book);

    Reserved findByBookAndBorrowIs(Book book,int borrow);

    List<Reserved> findAllByOvertimeAndBorrow(int overtime, int borrow);

    List<Reserved> findAllByUsernameAndBorrow(String username,int borrow);


}

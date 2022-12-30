package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.Borrow;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BorrowRepository extends CrudRepository<Borrow,Integer> {

    Borrow findBorrowByBookAndReverseIs(Book book,int reverse);
    List<Borrow> findAllByUserName(String userName);

    List<Borrow> findAllByBook(Book book);
    List<Borrow> findAllByAdminName(String adminName);

    List<Borrow> findAllByOvertimeAndReverse(int overtime, int reverse);

    List<Borrow> findAllByUserNameAndReverse(String userName,int reverse);

    List<Borrow> findAllByUserNameAndOvertimeAndReverse(String userName, int overtime, int reverse);
}

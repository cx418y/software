package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.Category;
import fudan.se.lab2.domain.Fine;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FineRepository extends CrudRepository<Fine,Integer> {
    List<Fine> findAllByUsername(String username);
    List<Fine> findAllByUsernameAndPaid(String username, int paid);
    List<Fine> findAllByPaid(int paid);

    Fine findByUsernameAndIsbnAndStartTime(String username, String isbn, String starttime);
}

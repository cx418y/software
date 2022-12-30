package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Category;
import fudan.se.lab2.domain.CreditRecord;
import fudan.se.lab2.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CreditRecordRepository extends CrudRepository<CreditRecord,Integer> {

    List<CreditRecord> findAllByUsername(String username);

}

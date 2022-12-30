package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
}

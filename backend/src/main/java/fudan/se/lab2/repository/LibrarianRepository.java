package fudan.se.lab2.repository;

import fudan.se.lab2.domain.Librarian;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
    Librarian findByUsername(String username);
    List<Librarian> findAll();
}

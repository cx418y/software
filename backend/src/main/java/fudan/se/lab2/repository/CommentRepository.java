package fudan.se.lab2.repository;
import fudan.se.lab2.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CommentRepository extends CrudRepository<Comment,Integer>{

    Comment findById (int id);

    Comment findByUsernameAndIsbn(String username, String isbn);

    //List<Comment> findAllByIsbn(String isbn);

    List<Comment> findAllByIsbn(String isbn);



}

package fudan.se.lab2.repository;
import fudan.se.lab2.domain.Comment;
import fudan.se.lab2.domain.Reply;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ReplyRepository extends CrudRepository<Reply,Integer>{

   //List<Reply> findAllByCid(int cid);

   List<Reply> findAllByCid(int cid);

   Reply findById(int id);



}
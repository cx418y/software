package fudan.se.lab2.repository;
import fudan.se.lab2.domain.Area;
import org.springframework.data.repository.CrudRepository;


public interface AreaRepository extends CrudRepository<Area,Integer>{

    Area findAreaByName(String name);


}

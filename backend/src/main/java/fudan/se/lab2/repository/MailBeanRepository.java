package fudan.se.lab2.repository;

import fudan.se.lab2.domain.MailBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MailBeanRepository extends CrudRepository<MailBean, Long> {
    List<MailBean> findByUsername(String username);
}

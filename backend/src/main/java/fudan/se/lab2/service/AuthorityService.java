package fudan.se.lab2.service;

import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public Authority getById(int id){
        Authority a = authorityRepository.findById(id);
        return a;
    }
    public Authority getByAuthority(String authority){
        Authority a = authorityRepository.findByAuthority(authority);
        return a;
    }

}

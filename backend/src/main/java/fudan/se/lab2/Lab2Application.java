package fudan.se.lab2;

import fudan.se.lab2.domain.Authority;
import fudan.se.lab2.domain.Librarian;
import fudan.se.lab2.domain.User;
import fudan.se.lab2.repository.AuthorityRepository;
import fudan.se.lab2.repository.LibrarianRepository;
import fudan.se.lab2.repository.UserRepository;
import fudan.se.lab2.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;

/**
 * Welcome to 2021 Software Engineering Lab2.
 * This is your first lab to write your own code and build a spring boot application.
 * Enjoy it :)
 *
 * @author LBW
 */
@SpringBootApplication
public class Lab2Application {
    @Autowired
    AuthorityService authorityService;
    @Autowired
    AuthorityRepository authorityRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }

    /**
     * This is a function to create some basic entities when the application starts.
     * Now we are using a In-Memory database, so you need it.
     * You can change it as you like.
     */
    @Bean
    public CommandLineRunner dataLoader(UserRepository userRepository, LibrarianRepository librarianRepository, PasswordEncoder passwordEncoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                // Create authorities if not exist.
               // Authority librarianAuthority = getOrCreateAuthority("Librarian", authorityRepository);
                //Authority teacherAuthority = getOrCreateAuthority("Teacher", authorityRepository);
                //Authority studentAuthority = getOrCreateAuthority("Student", authorityRepository);

                // Create an librarian if not exists.
                Authority teacher = authorityService.getByAuthority("teacher");
                if (userRepository.findByUsername("19302010066") == null) {
                    User user = new User(
                            "19302010066",
                            passwordEncoder.encode("123456"),
                            "libowen",
                            teacher
                         //   0,
                           // 0
                    );
                    userRepository.save(user);

                }

                if(librarianRepository.findByUsername("admin") == null){
                    Librarian librarian = new Librarian("admin",
                            passwordEncoder.encode("password"),
                            "librarian");
                    librarianRepository.save(librarian);
                }

            }

            private Authority getOrCreateAuthority(String authorityText, AuthorityRepository authorityRepository) {
                Authority authority = authorityRepository.findByAuthority(authorityText);
                if (authority == null) {
                    authority = new Authority(authorityText);
                    authorityRepository.save(authority);
                }
                return authority;
            }
        };
    }
}


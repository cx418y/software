package fudan.se.lab2.service;
import fudan.se.lab2.domain.Authority;

import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.BookState;
import fudan.se.lab2.domain.Category;
import fudan.se.lab2.repository.BookRepository;
import fudan.se.lab2.repository.BookStateRepository;
import fudan.se.lab2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStateService {
    @Autowired
    BookStateRepository bookStateRepository;

    public BookState get(int id) {
        BookState b= bookStateRepository.findById(id).orElse(null);
        return b;
    }
}

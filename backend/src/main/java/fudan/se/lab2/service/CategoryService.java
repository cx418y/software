package fudan.se.lab2.service;
import fudan.se.lab2.domain.Authority;

import fudan.se.lab2.domain.Book;
import fudan.se.lab2.domain.Category;
import fudan.se.lab2.repository.BookRepository;
import fudan.se.lab2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LBW
 */

@Service
public class CategoryService<CategoryDAO> {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> list() {
        List<Category> list =(List<Category>)  categoryRepository.findAll();
        return list;
    }

    public Category get(int id) {
        Category c= categoryRepository.findById(id).orElse(null);
        return c;
    }
}

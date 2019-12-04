package tranminhduc.pro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tranminhduc.pro.model.Book;
import tranminhduc.pro.model.Category;

public interface BookService {
    Page<Book> findAll(Pageable pageable);
    Book findById(Long id);
    void save(Book book);
    void remove(Long id);
    Iterable<Book> findAllByCategory(Category category);
    Page<Book> findAllByNameContaining(String name, Pageable pageable);

}

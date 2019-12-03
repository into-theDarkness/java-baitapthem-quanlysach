package tranminhduc.pro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tranminhduc.pro.model.Book;

public interface BookService {
    Page<Book> findAll(Pageable pageable);
    Book findById(Long id);
    void save(Book book);
    void remove(Long id);

}

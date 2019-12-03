package tranminhduc.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tranminhduc.pro.model.Book;
import tranminhduc.pro.repository.BookRepository;
import tranminhduc.pro.service.BookService;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);

    }

    @Override
    public void remove(Long id) {
        bookRepository.delete(id);

    }
}

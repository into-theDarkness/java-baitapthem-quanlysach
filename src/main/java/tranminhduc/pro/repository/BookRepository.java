package tranminhduc.pro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tranminhduc.pro.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {


}

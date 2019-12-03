package tranminhduc.pro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tranminhduc.pro.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}

package br.com.store.gmajor.category.repositories;
import br.com.store.gmajor.category.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<List<Category>> findAllByActiveTrue(Pageable pageable);
}

package br.com.store.gmajor.product.repositories;

import br.com.store.gmajor.category.domain.Category;
import br.com.store.gmajor.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<List<Product>> findAllByActiveTrue(Pageable pageable);

}

package br.com.store.gmajor.category.repositories;

import br.com.store.gmajor.category.domain.Category;
import br.com.store.gmajor.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CateroryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveTrue();
}

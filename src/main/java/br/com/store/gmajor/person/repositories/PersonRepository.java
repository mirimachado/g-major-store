package br.com.store.gmajor.person.repositories;

import br.com.store.gmajor.person.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>{

    Page<List<Person>> findAllByActiveTrue(Pageable pageable);
}

package br.com.store.gmajor.person.repositories;

import br.com.store.gmajor.person.domain.Person;
import br.com.store.gmajor.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>{

    List<Person> findAllByActiveTrue();
}

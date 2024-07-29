package br.com.store.gmajor.address.repositories;

import br.com.store.gmajor.address.domain.Address;
import br.com.store.gmajor.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Person> findAllByActiveTrue();
}

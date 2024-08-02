package br.com.store.gmajor.address.repositories;

import br.com.store.gmajor.address.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

   Page<List<Address>> findAllByActiveTrue(Pageable pageable);


}

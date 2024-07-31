package br.com.store.gmajor.user.repositories;

import br.com.store.gmajor.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    UserDetails findByToken(String token);

    Page<List<User>> findAllByActiveTrue(Pageable pageable);

}

package br.com.store.gmajor.cart.repositories;

import br.com.store.gmajor.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

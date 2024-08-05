package br.com.store.gmajor.cartitems.repositories;

import br.com.store.gmajor.cartitems.domain.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}

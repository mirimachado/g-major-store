package br.com.store.gmajor.cartitems.dto;

import br.com.store.gmajor.cart.domain.Cart;
import br.com.store.gmajor.product.domain.Product;
import jakarta.validation.constraints.NotBlank;

public record RequestCartItemsDTO(Long id,
                                  @NotBlank
                                  Cart cart,
                                  @NotBlank
                                  Product product,
                                  @NotBlank
                                  Integer quantity
                                  ) {
}

package br.com.store.gmajor.favorite.dto;

import br.com.store.gmajor.product.domain.Product;
import br.com.store.gmajor.user.domain.User;
import jakarta.validation.constraints.NotBlank;

public record RequestFavoriteDTO(Long id,
                                 @NotBlank
                                 User user,
                                 @NotBlank
                                 Product product) {
}

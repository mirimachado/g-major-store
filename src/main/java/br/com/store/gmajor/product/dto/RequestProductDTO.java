package br.com.store.gmajor.product.dto;

import br.com.store.gmajor.category.domain.Category;
import jakarta.validation.constraints.NotBlank;

public record RequestProductDTO(Long id,
                                @NotBlank
                                String name,
                                @NotBlank
                                String description,
                                @NotBlank
                                Category category,
                                @NotBlank
                                Double price,
                                @NotBlank
                                Integer quantity

                                ) {
}

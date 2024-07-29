package br.com.store.gmajor.category.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestCategoryDTO(
        Long id,
        @NotBlank
        String name,
        Boolean active,
        Boolean menuVisible) {
}

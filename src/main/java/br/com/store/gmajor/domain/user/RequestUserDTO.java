package br.com.store.gmajor.domain.user;

import jakarta.validation.constraints.NotBlank;

public record RequestUserDTO(Long id,
                             @NotBlank
                             String username,
                             @NotBlank
                             String email,
                             @NotBlank
                             String password) {

}

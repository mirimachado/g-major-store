package br.com.store.gmajor.person.dto;

import br.com.store.gmajor.user.domain.User;
import jakarta.validation.constraints.NotBlank;

public record RequestPersonDTO(Long id,
                               @NotBlank
                               String name,
                               @NotBlank
                               String phone,
                               @NotBlank
                               String cpf,
                               @NotBlank
                               User user
                               ) {
}

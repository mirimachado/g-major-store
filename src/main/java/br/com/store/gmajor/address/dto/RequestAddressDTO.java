package br.com.store.gmajor.address.dto;

import br.com.store.gmajor.person.domain.Person;
import jakarta.validation.constraints.NotBlank;
;

public record RequestAddressDTO(
        Long id,
        @NotBlank
        String number,
        @NotBlank
        String city,
        @NotBlank
        String state,
        @NotBlank
        String street,
        @NotBlank
        String neighborhood,
        @NotBlank
        Integer postalCode,
        String additionalInfo,
        Person person

        ) {
}

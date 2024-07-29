package br.com.store.gmajor.user.dto;

import br.com.store.gmajor.user.domain.UserRole;

public record RegisterRequestDTO (String username, String email, String password, UserRole role){
}

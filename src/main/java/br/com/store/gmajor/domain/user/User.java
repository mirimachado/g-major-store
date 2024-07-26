package br.com.store.gmajor.domain.user;

import br.com.store.gmajor.principal.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @Nullable
    private String token;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    private Boolean active;

    public User(RequestUserDTO requestUserDTO){
        this.username = requestUserDTO.username();
        this.email = requestUserDTO.email();
        this.password = requestUserDTO.password();
        this.active = true;

    }

}

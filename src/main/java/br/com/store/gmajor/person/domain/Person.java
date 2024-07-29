package br.com.store.gmajor.person.domain;

import br.com.store.gmajor.address.domain.Address;
import br.com.store.gmajor.user.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity(name = "persons")
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @Size(max = 11)
    @NotEmpty
    private String cpf;
    @NotEmpty
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    private Boolean active;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses;


    public Person(String phone, Long id, String name, String cpf, User user, Boolean active) {
        this.phone = phone;
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.user = user;
        this.active = true;
    }


}

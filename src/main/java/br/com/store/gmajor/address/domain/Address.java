package br.com.store.gmajor.address.domain;

import br.com.store.gmajor.person.domain.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "addresses")
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 8)
    private Integer postalCode;
    @NotEmpty
    private String state;
    @NotEmpty
    private String city;
    @NotEmpty
    private String neighborhood;
    @NotEmpty
    private String street;
    @NotEmpty
    private String number;
    @NotEmpty

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    private String additionalInfo;

    private Boolean active;

    public Address(String additionalInfo,
                   Long id,
                   Person person,
                   String number,
                   String street,
                   String neighborhood,
                   String city,
                   String state,
                   Integer postalCode) {
        this.id = id;
        this.active = true;
        this.person = person;
        this.number = number;
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.additionalInfo = additionalInfo;
    }



}



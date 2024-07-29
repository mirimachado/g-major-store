package br.com.store.gmajor.address.controllers;
import br.com.store.gmajor.address.domain.Address;
import br.com.store.gmajor.address.dto.RequestAddressDTO;
import br.com.store.gmajor.address.repositories.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressRepository repository;

    @GetMapping
    public ResponseEntity getAllAddresses(){

        var allAddresses = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allAddresses);

    }

    @PostMapping("/register")
    public ResponseEntity registerAddress(@RequestBody RequestAddressDTO data){
        Address newAddress = new Address();
        newAddress.setNumber(data.number());
        newAddress.setCity(data.city());
        newAddress.setNeighborhood(data.neighborhood());
        newAddress.setAdditionalInfo(data.additionalInfo());
        newAddress.setState(data.state());
        newAddress.setStreet(data.street());
        newAddress.setPostalCode(data.postalCode());

        Address savedAddress = this.repository.save(newAddress);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateAddress(@RequestBody @Valid RequestAddressDTO data) {
        Optional<Address> optionalAddress = repository.findById(data.id());
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setNumber(data.number());
            address.setCity(data.city());
            address.setNeighborhood(data.neighborhood());
            address.setAdditionalInfo(data.additionalInfo());
            address.setState(data.state());
            address.setStreet(data.street());
            address.setPostalCode(data.postalCode());

            repository.save(address);
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity deleteAddress(@PathVariable Long id){
            Optional<Address> optionalAddress = repository.findById(id);
            if (optionalAddress.isPresent()){
                Address address = optionalAddress.get();
                address.setActive(false);
                repository.save(address);
                return ResponseEntity.noContent().build();
            } else {
                throw new EntityNotFoundException();
            }

        }

    }







package br.com.store.gmajor.person.controllers;

import br.com.store.gmajor.person.domain.Person;
import br.com.store.gmajor.person.dto.RequestPersonDTO;
import br.com.store.gmajor.person.repositories.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository repository;


    @GetMapping
    public ResponseEntity getAllPersons(){

        var allPersons = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allPersons);


    }

    @PostMapping("/register")
    public ResponseEntity registerPerson(@RequestBody RequestPersonDTO data){
       Person newPerson = new Person();
       newPerson.setName(data.name());
       newPerson.setPhone(data.phone());
       newPerson.setCpf(data.cpf());
       newPerson.setUser(data.user());


       Person savedPerson = this.repository.save(newPerson);
       return ResponseEntity.ok().build();


    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePerson(@RequestBody @Valid RequestPersonDTO data){
        Optional<Person> optionalPerson = repository.findById(data.id());
        if (optionalPerson.isPresent()){
            Person person = optionalPerson.get();
            person.setName(data.name());
            person.setPhone(data.phone());
            person.setCpf(data.cpf());
            person.setUser(data.user());

            repository.save(person);
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePerson(@PathVariable Long id){
        Optional<Person> optionalPerson = repository.findById(id);
        if (optionalPerson.isPresent()){
            Person person = optionalPerson.get();
            person.setActive(false);
            repository.save(person);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }



}

package br.com.store.gmajor.controllers;

import br.com.store.gmajor.domain.user.RequestUserDTO;
import br.com.store.gmajor.domain.user.User;
import br.com.store.gmajor.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity getAllUsers(){
        var allUsers = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestUserDTO data){
        User newUser = new User(data);
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid RequestUserDTO data){
        Optional<User> optionalUser = repository.findById(data.id());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setUsername(data.username());
            user.setEmail(data.email());
            user.setPassword(data.email());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setActive(false);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }


}

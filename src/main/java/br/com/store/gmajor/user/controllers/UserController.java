package br.com.store.gmajor.user.controllers;

import br.com.store.gmajor.user.dto.RequestUserDTO;
import br.com.store.gmajor.user.domain.User;
import br.com.store.gmajor.user.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity getAllUsers(Pageable pageable){
        Page<List<User>> users = repository.findAllByActiveTrue(pageable);
        return ResponseEntity.ok(users);

    }


    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody @Valid RequestUserDTO data){
        Optional<User> optionalUser = repository.findById(data.id());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setUsername(data.username());
            user.setEmail(data.email());
            repository.save(user);
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
            repository.save(user);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }


}

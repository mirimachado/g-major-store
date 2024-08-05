package br.com.store.gmajor.user.controllers;


import br.com.store.gmajor.cart.domain.Cart;
import br.com.store.gmajor.cart.repositories.CartRepository;
import br.com.store.gmajor.user.domain.User;
import br.com.store.gmajor.user.dto.LoginRequestDTO;
import br.com.store.gmajor.user.dto.RegisterRequestDTO;
import br.com.store.gmajor.user.dto.ResponseDTO;
import br.com.store.gmajor.user.infra.services.TokenService;
import br.com.store.gmajor.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final CartRepository repositoryCart;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity login (@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(user.getPassword(), body.password())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));

        }return ResponseEntity.badRequest().build();

    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());
        if (user.isEmpty()){
            User newUser = new User();
            Cart newCart = new Cart();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setUsername(body.username());
            newUser.setRole(body.role());
            this.repository.save(newUser);
            this.repositoryCart.save(newCart);
                String token = this.tokenService.generateToken(newUser);
                return ResponseEntity.ok(new ResponseDTO(newUser.getPassword(), token));
        }
        return ResponseEntity.badRequest().build();

    }



}

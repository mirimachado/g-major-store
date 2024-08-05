package br.com.store.gmajor.cartitems.controllers;
import br.com.store.gmajor.cartitems.domain.CartItems;
import br.com.store.gmajor.cartitems.dto.RequestCartItemsDTO;
import br.com.store.gmajor.cartitems.repositories.CartItemsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartitems")
public class CartItemsController {

    @Autowired
    private CartItemsRepository repository;

    @GetMapping
    public ResponseEntity getAllCartItem(Pageable pageable){

        Page<CartItems> cartItems = repository.findAll(pageable);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/register")
    public ResponseEntity registerCartItem(@RequestBody RequestCartItemsDTO data) {
        CartItems newCartItem = new CartItems();
        newCartItem.setCart(data.cart());
        newCartItem.setProduct(data.product());
        newCartItem.setQuantity(data.quantity());
        CartItems savedCartItem = this.repository.save(newCartItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCartItem(@RequestBody @Valid RequestCartItemsDTO data) {
        Optional<CartItems> optionalCartItem = repository.findById(data.id());
        if (optionalCartItem.isPresent()) {
            CartItems cartItem = optionalCartItem.get();
            cartItem.setProduct(data.product());
            cartItem.setQuantity(data.quantity());
            cartItem.setCart(data.cart());
            repository.save(cartItem);
            return ResponseEntity.ok(cartItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCartItem(@PathVariable Long id) {
        Optional<CartItems> OptionalCartItem = repository.findById(id);
        if (OptionalCartItem.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }



}

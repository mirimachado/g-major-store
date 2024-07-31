package br.com.store.gmajor.product.controllers;

import br.com.store.gmajor.product.domain.Product;
import br.com.store.gmajor.product.dto.RequestProductDTO;
import br.com.store.gmajor.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts(Pageable pageable){
        Page<List<Product>> products = repository.findAllByActiveTrue(pageable);
        return ResponseEntity.ok(products);
    }


    @PostMapping("/register")
    public ResponseEntity registerProduct(@RequestBody RequestProductDTO data){
        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setCategory(data.category());
        newProduct.setDescription(data.description());
        newProduct.setQuantity(data.quantity());
        newProduct.setPrice(data.price());

        Product savedProduct = this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    @Transactional
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProductDTO data){
        Optional<Product> optionalProduct= repository.findById(data.id());
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setCategory(data.category());
            product.setDescription(data.description());
            product.setQuantity(data.quantity());
            product.setPrice(data.price());

            repository.save(product);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setActive(false);
            repository.save(product);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }


}

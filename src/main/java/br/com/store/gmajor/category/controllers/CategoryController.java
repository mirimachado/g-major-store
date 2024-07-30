package br.com.store.gmajor.category.controllers;


import br.com.store.gmajor.category.domain.Category;
import br.com.store.gmajor.category.dto.RequestCategoryDTO;
import br.com.store.gmajor.category.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public ResponseEntity getAllCategories(){

        var allCategories = repository.findAllByActiveTrue();
        return ResponseEntity.ok(allCategories);

    }

    @PostMapping("/register")
    public ResponseEntity registerCategory(@RequestBody RequestCategoryDTO data){
        Category newCategory = new Category();
        newCategory.setName(data.name());
        newCategory.setMenuVisible(data.menuVisible());

        Category savedCategory = this.repository.save(newCategory);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    @Transactional
    public ResponseEntity updateCategory(@RequestBody @Valid RequestCategoryDTO data){
        Optional<Category> optionalCategory= repository.findById(data.id());
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(data.name());
            category.setMenuVisible(data.menuVisible());

            repository.save(category);
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCategory(@PathVariable Long id){
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setActive(false);
            repository.save(category);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }




}

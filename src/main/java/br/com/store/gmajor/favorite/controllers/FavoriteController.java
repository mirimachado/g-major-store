package br.com.store.gmajor.favorite.controllers;
import br.com.store.gmajor.favorite.domain.Favorite;
import br.com.store.gmajor.favorite.dto.RequestFavoriteDTO;
import br.com.store.gmajor.favorite.repositories.FavoriteRepository;
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
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteRepository repository;


    @GetMapping
    public ResponseEntity getAllFavorites(Pageable pageable) {
        Page<Favorite> favorites = repository.findAll(pageable);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping("/register")
    public ResponseEntity registerFavorite(@RequestBody RequestFavoriteDTO data) {
        Favorite newFavorite = new Favorite();
        newFavorite.setUser(data.user());
        newFavorite.setProduct(data.product());
        Favorite savedFavorite = this.repository.save(newFavorite);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateFavorite(@RequestBody @Valid RequestFavoriteDTO data) {
        Optional<Favorite> optionalFavorite = repository.findById(data.id());
        if (optionalFavorite.isPresent()) {
            Favorite favorite = optionalFavorite.get();
            favorite.setProduct(data.product());
            favorite.setUser(data.user());
            repository.save(favorite);
            return ResponseEntity.ok(favorite);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteFavorite(@PathVariable Long id) {
        Optional<Favorite> OptionalFavorite = repository.findById(id);
        if (OptionalFavorite.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException();
        }

    }
}

package br.com.store.gmajor.favorite.repositories;

import br.com.store.gmajor.favorite.domain.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Page<Favorite> findAll(Pageable pageable);


}

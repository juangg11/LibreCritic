package com.videogame.librecritic.repository;

import com.videogame.librecritic.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByVideogameIdOrderByCreatedAtDesc(Long videogameId);
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);
}

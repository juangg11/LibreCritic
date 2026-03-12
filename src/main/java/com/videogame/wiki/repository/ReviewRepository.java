package com.videogame.wiki.repository;

import com.videogame.wiki.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByVideogameIdOrderByCreatedAtDesc(Long videogameId);
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);
}

package com.videogame.librecritic.service;

import com.videogame.librecritic.model.Review;
import com.videogame.librecritic.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> findByVideogameId(Long videogameId) {
        return reviewRepository.findByVideogameIdOrderByCreatedAtDesc(videogameId);
    }

    public List<Review> findByUserId(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}

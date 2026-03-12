package com.videogame.wiki.controller;

import com.videogame.wiki.model.Review;
import com.videogame.wiki.model.User;
import com.videogame.wiki.model.Videogame;
import com.videogame.wiki.service.ReviewService;
import com.videogame.wiki.service.UserService;
import com.videogame.wiki.service.VideogameService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final VideogameService videogameService;

    public ReviewController(ReviewService reviewService, UserService userService, VideogameService videogameService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.videogameService = videogameService;
    }

    @PostMapping("/add")
    public String addReview(@RequestParam Long videogameId,
                            @RequestParam String comment,
                            @RequestParam boolean positive,
                            Authentication authentication) {
        
        User user = userService.findByUsername(authentication.getName());
        Optional<Videogame> videogame = videogameService.findById(videogameId);

        if (videogame.isPresent()) {
            Review review = new Review();
            review.setUser(user);
            review.setVideogame(videogame.get());
            review.setComment(comment);
            review.setPositive(positive);
            reviewService.save(review);
        }

        return "redirect:/videogames/" + videogameId;
    }

    @PostMapping("/{id}/delete")
    public String deleteReview(@PathVariable Long id, @RequestParam Long videogameId) {
        reviewService.deleteById(id);
        return "redirect:/videogames/" + videogameId;
    }
}

package com.videogame.wiki.controller;

import com.videogame.wiki.model.Review;
import com.videogame.wiki.model.User;
import com.videogame.wiki.service.ReviewService;
import com.videogame.wiki.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ProfileController {

    private final UserService userService;
    private final ReviewService reviewService;

    public ProfileController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping("/profile")
    public String myProfile(Model model, Authentication authentication) {
        return viewUserProfile(authentication.getName(), model, authentication);
    }

    @GetMapping("/profile/{username}")
    public String viewUserProfile(@PathVariable String username, Model model, Authentication authentication) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return "redirect:/";
        }
        
        List<Review> reviews = reviewService.findByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);
        model.addAttribute("isOwnProfile", authentication != null && authentication.getName().equals(username));
        
        if (authentication != null) {
            User loggedInUser = userService.findByUsername(authentication.getName());
            model.addAttribute("isFollowing", loggedInUser.getFollowing().contains(user));
        }

        return "profile";
    }

    @PostMapping("/profile/update-bio")
    public String updateBio(@RequestParam String bio, Authentication authentication) {
        userService.updateBio(authentication.getName(), bio);
        return "redirect:/profile";
    }

    @PostMapping("/follow/{username}")
    public String follow(@PathVariable String username, Authentication authentication) {
        userService.followUser(authentication.getName(), username);
        return "redirect:/profile/" + username;
    }

    @PostMapping("/unfollow/{username}")
    public String unfollow(@PathVariable String username, Authentication authentication) {
        userService.unfollowUser(authentication.getName(), username);
        return "redirect:/profile/" + username;
    }

    @GetMapping("/users/search")
    public String searchUsers(@RequestParam String query, Model model) {
        List<User> users = userService.searchUsers(query);
        model.addAttribute("users", users);
        model.addAttribute("query", query);
        return "user_search";
    }
}

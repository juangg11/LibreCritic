package com.videogame.wiki.controller;

import com.videogame.wiki.dto.UserRegistrationDto;
import com.videogame.wiki.model.User;
import com.videogame.wiki.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto,
                                      BindingResult result,
                                      Model model) {
        User existingUser = userService.findByUsername(registrationDto.getUsername());

        if (existingUser != null && existingUser.getUsername() != null && !existingUser.getUsername().isEmpty()) {
            result.rejectValue("username", null, "There is already an account registered with the same username");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", registrationDto);
            return "register";
        }

        userService.save(registrationDto);
        return "redirect:/register?success";
    }
}

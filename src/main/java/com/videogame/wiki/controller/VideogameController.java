package com.videogame.wiki.controller;

import com.videogame.wiki.model.Videogame;
import com.videogame.wiki.service.VideogameService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class VideogameController {

    private final VideogameService videogameService;
    private final com.videogame.wiki.service.ReviewService reviewService;
    private static final String UPLOAD_DIRECTORY = "./uploads";

    public VideogameController(VideogameService videogameService, com.videogame.wiki.service.ReviewService reviewService) {
        this.videogameService = videogameService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public String index(Model model,
                        @CookieValue(value = "visit_count", defaultValue = "0") String visitCount,
                        HttpServletResponse response,
                        HttpSession session) {
        
        int count = Integer.parseInt(visitCount) + 1;
        Cookie cookie = new Cookie("visit_count", String.valueOf(count));
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);

        model.addAttribute("videogames", videogameService.findAll());
        model.addAttribute("visitCount", count);

        String lastViewedGame = (String) session.getAttribute("lastViewedGameTitle");
        model.addAttribute("lastViewedGame", lastViewedGame);

        return "index";
    }

    @GetMapping("/videogames/{id}")
    public String show(@PathVariable Long id, Model model, HttpSession session) {
        Optional<Videogame> videogame = videogameService.findById(id);
        if (videogame.isPresent()) {
            model.addAttribute("videogame", videogame.get());
            model.addAttribute("reviews", reviewService.findByVideogameId(id));
            session.setAttribute("lastViewedGameTitle", videogame.get().getTitle());
            return "videogame_detail";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/videogames/new")
    public String createForm(Model model) {
        model.addAttribute("videogame", new Videogame());
        return "videogame_form";
    }

    @PostMapping("/videogames")
    public String save(@Valid @ModelAttribute("videogame") Videogame videogame,
                       BindingResult result,
                       @RequestParam("image") MultipartFile file,
                       Model model) {
        if (result.hasErrors()) {
            return "videogame_form";
        }

        saveImageFile(videogame, file);
        videogameService.save(videogame);
        return "redirect:/";
    }

    @GetMapping("/videogames/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Videogame> videogame = videogameService.findById(id);
        if (videogame.isPresent()) {
            model.addAttribute("videogame", videogame.get());
            return "videogame_form";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/videogames/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("videogame") Videogame videogame,
                         BindingResult result,
                         @RequestParam("image") MultipartFile file,
                         Model model) {
        if (result.hasErrors()) {
            return "videogame_form";
        }

        Optional<Videogame> existingOpt = videogameService.findById(id);
        if (existingOpt.isPresent()) {
            Videogame existing = existingOpt.get();
            videogame.setId(existing.getId());
            if (file.isEmpty()) {
                videogame.setCoverImagePath(existing.getCoverImagePath());
            } else {
                saveImageFile(videogame, file);
            }
            videogameService.save(videogame);
            return "redirect:/videogames/" + id;
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/videogames/{id}/delete")
    public String delete(@PathVariable Long id) {
        videogameService.deleteById(id);
        return "redirect:/";
    }

    private void saveImageFile(Videogame videogame, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);
                videogame.setCoverImagePath(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

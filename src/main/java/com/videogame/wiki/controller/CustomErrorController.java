package com.videogame.wiki.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorTitle", "404 - Not Found");
                model.addAttribute("errorMessage", "The page you are looking for was not found.");
                return "404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorTitle", "403 - Forbidden");
                model.addAttribute("errorMessage", "You do not have permission to access this page.");
                return "403";
            }
        }

        model.addAttribute("errorTitle", "Error");
        model.addAttribute("errorMessage", "An unexpected error occurred.");
        return "error";
    }

    @GetMapping("/403")
    public String accessDenied(Model model) {
        model.addAttribute("errorTitle", "403 - Forbidden");
        model.addAttribute("errorMessage", "You do not have permission to access this page.");
        return "403";
    }
}

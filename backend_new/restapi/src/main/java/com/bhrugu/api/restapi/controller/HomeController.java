package com.bhrugu.api.restapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller for the Digital Video Store
 * Provides the main home page when accessing the root URL
 */
@Controller
public class HomeController {
    
    /**
     * GET / - Display the home page
     * @param model Spring MVC model for passing data to the view
     * @return The home page template name
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Digital Video Store");
        model.addAttribute("message", "Welcome to your comprehensive Digital Video Store API!");
        return "home";
    }
}

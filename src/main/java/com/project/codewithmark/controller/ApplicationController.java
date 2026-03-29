package com.project.codewithmark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.codewithmark.model.enums.ServiceTypeEnum;

@Controller
public class ApplicationController {

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("types", ServiceTypeEnum.values());

        return "index"; // This will return the view named "index.html" in the templates directory
    }

    @GetMapping("/terms")
    public String showTerms() {
        return "terms";
    }

    @GetMapping("/privacy")
    public String showPrivacy() {
        return "privacy";
    }

    @GetMapping("/contact_us")
    public String showContactUs() {
        return "contact_us";
    }

    @GetMapping("/user")
    public String showUserDashboard(Model model) {
        model.addAttribute("types", ServiceTypeEnum.values());
        return "user";
    }

    @GetMapping("/therapist")
    public String showTherapistDashboard(Model model) {
        model.addAttribute("types", ServiceTypeEnum.values());
        return "therapist";
    }

    @GetMapping("/admin")
    public String showAdminDashboard(Model model) {
        return "admin";
    }
}

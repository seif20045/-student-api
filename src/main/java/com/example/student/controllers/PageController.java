package com.example.student.controllers;

import com.example.student.model.User;
import com.example.student.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/show")
    public String show() {
        return "show";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register-form")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password) {
        return "redirect:/login";

    }
}
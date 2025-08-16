package com.example.student.controllers;

import com.example.student.model.Role;
import com.example.student.model.User;
import com.example.student.repository.RoleRepo;
import com.example.student.repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class PageController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public String registerUser(@Valid @RequestParam String username, @RequestParam String email, @RequestParam String password) {
        if (username.isBlank() || email.isBlank() || password.isBlank()) {

            return "redirect:/register";
        }

        Role roleUser = roleRepo.findByName("ROLE_USER");
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(roleUser));

        userRepo.save(user);

        return "redirect:/login";

    }
}
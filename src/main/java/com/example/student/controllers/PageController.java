package com.example.student.controllers;

import com.example.student.dtos.UserRegister;
import com.example.student.model.Role;
import com.example.student.model.User;
import com.example.student.repository.RoleRepo;
import com.example.student.repository.UserRepo;
import com.example.student.service.PageService;
import com.example.student.service.PageServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class PageController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private PageService pageService;


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
    public String registerUser(@Valid @ModelAttribute UserRegister registerDto) {

        return pageService.register(registerDto);
    }
}
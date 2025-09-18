package com.example.student.controllers;

import com.example.student.dtos.UserRegister;
import com.example.student.service.PageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
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
    public String register(Model model) {
        model.addAttribute("userRegister", new UserRegister());
        return "register"; // بيرجع لملف register.html
    }

    @PostMapping("/register-form")
    public String registerUser(@Valid @ModelAttribute("userRegister") UserRegister registerDto) {
        System.out.println("DTO from form => " + registerDto);
        return pageService.register(registerDto);
    }
}

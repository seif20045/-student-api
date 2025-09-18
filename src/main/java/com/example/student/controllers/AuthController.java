package com.example.student.controllers;

import com.example.student.dtos.UserLogin;
import com.example.student.dtos.UserRegister;
import com.example.student.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "login and register")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "register", description = "register new user")
    public ResponseEntity<String> register(@RequestBody UserRegister request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "login", description = "with email and password")

    public ResponseEntity<String> login(@RequestBody @Valid UserLogin loginDto) {
        return authService.login(loginDto);
    }
}

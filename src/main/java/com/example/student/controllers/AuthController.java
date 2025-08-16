package com.example.student.controllers;

import com.example.student.dto.RegisterRequest;
import com.example.student.model.Role;
import com.example.student.model.User;
import com.example.student.repository.RoleRepo;
import com.example.student.repository.UserRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> reregister(@RequestBody RegisterRequest request) {

        if (userRepo.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        Role roleUser = roleRepo.findByName("ROLE_USER");
        if (roleUser == null) {
            return ResponseEntity.badRequest().body("Role ROLE_USER not found in DB");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(roleUser));

        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        User user = userRepo.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not found");

        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful for " + user.getName());
    }


}




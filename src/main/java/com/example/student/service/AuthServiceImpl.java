package com.example.student.service;

import com.example.student.dtos.UserLogin;
import com.example.student.dtos.UserRegister;
import com.example.student.entity.Role;
import com.example.student.entity.User;
import com.example.student.mapper.UserMapper;
import com.example.student.repository.RoleRepo;
import com.example.student.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseEntity<String> register(UserRegister registerDto) {
        if (userRepo.findByEmail(registerDto.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        Role roleUser = roleRepo.findByName("ROLE_USER");
        if (roleUser == null) {
            return ResponseEntity.badRequest().body("Role ROLE_USER not found in DB");
        }

        User user = userMapper.toEntity(registerDto);
        System.out.println("Mapped User: " + user);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Set.of(roleUser));

        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }


    @Override
    public ResponseEntity<String> login(UserLogin loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        User user = userRepo.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful for " + user.getName());
    }
}

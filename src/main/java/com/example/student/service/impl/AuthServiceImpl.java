package com.example.student.service.impl;

import com.example.student.dtos.UserLogin;
import com.example.student.dtos.UserRegister;
import com.example.student.entity.Role;
import com.example.student.entity.User;
import com.example.student.exceptionHandling.EmailAlreadyExistsException;
import com.example.student.exceptionHandling.InvalidCredentialsException;
import com.example.student.mapper.UserMapper;
import com.example.student.repository.RoleRepo;
import com.example.student.repository.UserRepo;
import com.example.student.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new EmailAlreadyExistsException("Email already exists with email: " + registerDto.getEmail());
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
            throw new InvalidCredentialsException("User not found with email: " + email);

        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password for user: " + email);

        }

        return ResponseEntity.ok("Login successful for " + user.getName());
    }
}

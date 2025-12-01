package com.example.student.service;

import com.example.student.dtos.UserLogin;
import com.example.student.dtos.UserRegister;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(UserRegister registerDto);
    ResponseEntity<String> login(UserLogin loginDto);
}

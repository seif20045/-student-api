package com.example.student.service.impl;

import com.example.student.dtos.UserRegister;
import com.example.student.entity.Role;
import com.example.student.entity.User;
import com.example.student.mapper.UserMapper;
import com.example.student.repository.RoleRepo;
import com.example.student.repository.UserRepo;
import com.example.student.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper Mapper;

    @Override
    public String register(UserRegister registerDto) {
        if (registerDto.getName().isBlank() || registerDto.getEmail().isBlank() || registerDto.getPassword().isBlank()) {

            return "redirect:/register";
        }
        if (userRepo.findByEmail(registerDto.getEmail()) != null) {
            return "redirect:/register?error=email_exists";
        }


        Role roleUser = roleRepo.findByName("ROLE_USER");
        User user = Mapper.toEntity(registerDto); ;
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Set.of(roleUser));
        userRepo.save(user);

        return "redirect:/login";


    }
}

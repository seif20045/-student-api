package com.example.student.security;

import com.example.student.model.User;
import com.example.student.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceStudent implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

//

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("not found " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getRoles());

    }
}

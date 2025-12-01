package com.example.student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {


    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth

                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN")

                .requestMatchers("/register", "/login", "/register-form").permitAll()

                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                .requestMatchers(HttpMethod.GET, "/studentapi/student/**").hasAnyRole("USER", "ADMIN")

                .requestMatchers(HttpMethod.POST, "/studentapi/student").hasRole("ADMIN")

                .anyRequest().authenticated()).formLogin(form -> form.loginPage("/login")

                .defaultSuccessUrl("/show", true).permitAll());

        return http.build();
    }


}

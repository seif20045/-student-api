package com.example.student.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()

                .requestMatchers(HttpMethod.GET, "/studentapi/student/**")

                .hasAnyRole("USER", "ADMIN").requestMatchers(HttpMethod.POST, "/studentapi/student").hasAnyRole("ADMIN")
                .anyRequest().authenticated();


        return http.build();
    }


}

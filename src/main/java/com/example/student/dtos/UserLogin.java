package com.example.student.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLogin {

    @NotBlank(message = " required")
    private String email;
    @NotBlank(message = " required")
    private String password;

}

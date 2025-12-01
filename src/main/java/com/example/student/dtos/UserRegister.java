package com.example.student.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class UserRegister {

    @NotBlank(message = " required")
    private String name;
    @NotBlank(message = " required")
    private String email;
    @NotBlank(message = " required")
    private String password;


}

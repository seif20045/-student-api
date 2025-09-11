package com.example.student.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDto {

    @NotBlank(message = "not null")
    private String course;
    private int grade;
    private int id;


}

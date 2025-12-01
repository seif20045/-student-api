package com.example.student.controllers;


import com.example.student.dtos.StudentDto;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentapi")
@Tag(name = "studentController")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Student create(@RequestBody StudentDto studentDto) {

        return studentService.createStudent(studentDto);
    }


    @GetMapping("/student/{id}")
    @Operation(summary = "Get student by ID", description = "Fetch a student using their ID")

    public StudentDto show(@PathVariable("id") int id) {

        return studentService.ShowStudentById(id);
    }


}

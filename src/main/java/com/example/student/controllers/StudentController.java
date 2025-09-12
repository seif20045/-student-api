package com.example.student.controllers;


import com.example.student.dtos.StudentDto;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentapi")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/student")
    @PreAuthorize("hasRole('ADMIN')")
    public Student create(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto show(@PathVariable("id") int id) {

        return studentService.ShowStudentById(id);
    }


}

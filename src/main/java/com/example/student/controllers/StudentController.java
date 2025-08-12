package com.example.student.controllers;


import com.example.student.model.Student;
import com.example.student.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentApi")
public class StudentController {

    @Autowired
    StudentRepo repo;




    @PostMapping("/student")
    public Student create(@RequestBody Student student) {
        return repo.save(student);
    }

    @GetMapping("/student/{id}")
public Student show(@PathVariable("id") int id  ){

        return repo.findById(id).orElse(null);
    }







}

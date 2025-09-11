package com.example.student.service;

import com.example.student.dtos.StudentDto;
import com.example.student.model.Student;

public interface StudentService {
    Student createStudent(StudentDto studentDto);
    StudentDto ShowStudentById(int id);



}

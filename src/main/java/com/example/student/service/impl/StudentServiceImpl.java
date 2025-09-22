package com.example.student.service.impl;

import com.example.student.dtos.StudentDto;
import com.example.student.entity.Student;
import com.example.student.exceptionHandling.StudentNotFoundException;
import com.example.student.mapper.StudentMapper;
import com.example.student.repository.StudentRepo;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    StudentRepo repo;


    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        return repo.save(student);
    }

    @Override
    public StudentDto ShowStudentById(int id) {
        return repo.findById(id).map(studentMapper::tostudentDto)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

    }
}

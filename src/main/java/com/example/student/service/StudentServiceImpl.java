package com.example.student.service;

import com.example.student.dtos.StudentDto;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo repo;


    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setCourse(studentDto.getCourse());
        student.setGrade(studentDto.getGrade());
        return repo.save(student);
    }

    @Override
    public StudentDto ShowStudentById(int id) {
        return repo.findById(id).map(student -> {
            StudentDto dto = new StudentDto();
            dto.setCourse(student.getCourse());
            dto.setGrade(student.getGrade());
            return dto;
        }).orElse(null);
    }
}

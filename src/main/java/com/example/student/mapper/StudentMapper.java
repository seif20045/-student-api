package com.example.student.mapper;

import com.example.student.dtos.StudentDto;
import com.example.student.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentDto studentDto);

    StudentDto tostudentDto(Student student);

}

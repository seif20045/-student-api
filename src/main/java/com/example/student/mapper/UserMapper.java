package com.example.student.mapper;



import com.example.student.dtos.UserRegister;
import com.example.student.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    // Dto ->  Entity  كدا بنحول من
    User toEntity(UserRegister registerDto);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    // Entity ->  Dto  كدا بنحول من
    UserRegister toRegisterDto(User user);

    //LOGIN
 /*
    // UserLogin -> User
    User toEntity(UserLogin loginDto);

    // User -> UserLogin
    UserLogin toLoginDto(User user);
*/

}

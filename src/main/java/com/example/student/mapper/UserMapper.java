package com.example.student.mapper;



import com.example.student.dtos.UserRegister;
import com.example.student.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Dto ->  Entity  كدا بنحول من
    User toEntity(UserRegister registerDto);

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

package com.example.student.mapper;



import com.example.student.dtos.UserRegister;
import com.example.student.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "email", target = "email")
    // Dto ->  Entity  كدا بنحول من
    User toEntity(UserRegister registerDto);
    @Mapping(source = "email", target = "email")
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

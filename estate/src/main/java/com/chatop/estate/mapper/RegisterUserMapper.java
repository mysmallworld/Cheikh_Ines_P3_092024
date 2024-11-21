package com.chatop.estate.mapper;

import com.chatop.estate.Dto.RegisterUserDto;
import com.chatop.estate.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegisterUserMapper {
    public User toEntity(RegisterUserDto newUserDto){
        if(newUserDto == null){
            return null;
        }
        User newUser = new User();
        newUser.setEmail(newUserDto.getEmail());
        newUser.setPassword(newUserDto.getPassword());
        newUser.setName(newUserDto.getName());
        LocalDateTime now = LocalDateTime.now();
        newUser.setCreatedAt(now);
        newUser.setUpdatedAt(now);

        return newUser;
    }
}

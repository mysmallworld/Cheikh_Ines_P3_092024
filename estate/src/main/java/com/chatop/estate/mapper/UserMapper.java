package com.chatop.estate.mapper;

import com.chatop.estate.dto.RegisterUserDto;
import com.chatop.estate.dto.UserResponseDto;
import com.chatop.estate.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public UserResponseDto mapUserToDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

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

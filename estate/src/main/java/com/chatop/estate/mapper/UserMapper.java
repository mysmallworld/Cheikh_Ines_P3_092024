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

    public User toEntity(RegisterUserDto newUserDto) {
        if (newUserDto == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();

        return User.builder()
                .email(newUserDto.getEmail())
                .password(newUserDto.getPassword())
                .name(newUserDto.getName())
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}

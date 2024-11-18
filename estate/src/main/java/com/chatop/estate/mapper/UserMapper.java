package com.chatop.estate.mapper;

import com.chatop.estate.Dto.UserResponseDto;
import com.chatop.estate.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapUserToDto(Users user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}

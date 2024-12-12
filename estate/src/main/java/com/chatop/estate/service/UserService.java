package com.chatop.estate.service;

import com.chatop.estate.dto.UserResponseDto;
import com.chatop.estate.mapper.UserMapper;
import com.chatop.estate.model.User;
import com.chatop.estate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserResponseDto getUserById(UUID id) {
        try {
            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }

            User user = optionalUser.get();

            UserResponseDto userResponseDto = userMapper.mapUserToDto(user);

            return userResponseDto;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}

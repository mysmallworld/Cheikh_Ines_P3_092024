package com.chatop.estate.service;

import com.chatop.estate.dto.UserResponseDto;
import com.chatop.estate.mapper.UserMapper;
import com.chatop.estate.model.User;
import com.chatop.estate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserResponseDto getUser(String authorizationHeader) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                throw new RuntimeException("Authorization header must start with Bearer");
            }
            String token = authorizationHeader.substring(7);

            Map<String, Object> userToken = jwtService.decodeToken(token);
            String userEmail = (String) userToken.get("sub");
            User user = userRepository.findByEmail(userEmail);

            if (user == null) {
                throw new RuntimeException("User not found");
            }

            UserResponseDto userResponseDto = userMapper.mapUserToDto(user);

            return userResponseDto;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the user: " + e.getMessage());
        }
    }
}

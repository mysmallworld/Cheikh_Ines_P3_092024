package com.chatop.estate.service;

import com.chatop.estate.Dto.LoginUserDto;
import com.chatop.estate.Dto.RegisterUserDto;
import com.chatop.estate.Dto.UserResponseDto;
import com.chatop.estate.configuration.AuthConfig;
import com.chatop.estate.mapper.RegisterUserMapper;
import com.chatop.estate.mapper.UserMapper;
import com.chatop.estate.model.User;
import com.chatop.estate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthConfig authconfig;

    @Autowired
    RegisterUserMapper registerUserMapper;

    @Autowired
    UserMapper userMapper;

    public String registerUser(RegisterUserDto userDto) {
        try {
            User user = userRepository.findByEmail(userDto.getEmail());
            if (user != null && user.getEmail().equals(userDto.getEmail())) {
                throw new Exception("User already exists");
            }
            User newUser = registerUserMapper.toEntity(userDto);
            newUser.setPassword(authconfig.passwordEncoder().encode(newUser.getPassword()));
            userRepository.save(newUser);
            String token = jwtService.generateToken(newUser);
            return token;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during user registration: " + e.getMessage());
        }
    }

    public String loginUser(LoginUserDto userDto) {
        try {
            User user = userRepository.findByEmail(userDto.getEmail());
            Boolean isSamePassword = authconfig.passwordEncoder().matches(userDto.getPassword(), user.getPassword());
            if ((user != null) && !isSamePassword) {
                throw new Exception("User password not same");
            }
            String token = jwtService.generateToken(user);
            return token;
        } catch (java.lang.Exception e) {
            throw new RuntimeException("An error occurred during user login: " + e.getMessage());
        }
    }

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

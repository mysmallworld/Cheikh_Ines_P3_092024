package com.chatop.estate.service;

import com.chatop.estate.Dto.UsersDto;
import com.chatop.estate.configuration.AuthConfig;
import com.chatop.estate.mapper.UserMapper;
import com.chatop.estate.model.Users;
import com.chatop.estate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthConfig authconfig;

    @Autowired
    UserMapper userMapper;

    public String registerUser(UsersDto userDto) {
        try {
            Users user = userRepository.findByEmail(userDto.getEmail());
            if (user != null && user.getEmail().equals(userDto.getEmail())) {
                throw new Exception("User already exists");
            }
            Users newUser = userMapper.toEntity(userDto);
            newUser.setPassword(authconfig.passwordEncoder().encode(newUser.getPassword()));
            userRepository.save(newUser);
            String token = jwtService.generateToken(newUser);
            return token;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during user registration: " + e.getMessage());
        }
    }


    public String loginUser(UsersDto userDto) {
        Users user = userRepository.findByEmail(userDto.getEmail());
        String token = jwtService.generateToken(user);
        return token;
    }

    public String getUser() {
        return "";
    }
}

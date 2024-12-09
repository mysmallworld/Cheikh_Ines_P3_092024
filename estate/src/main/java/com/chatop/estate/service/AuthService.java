package com.chatop.estate.service;

import com.chatop.estate.dto.LoginUserDto;
import com.chatop.estate.dto.RegisterUserDto;
import com.chatop.estate.configuration.AuthConfig;
import com.chatop.estate.mapper.UserMapper;
import com.chatop.estate.model.User;
import com.chatop.estate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthConfig authconfig;

    @Autowired
    UserMapper userMapper;

    public String registerUser(RegisterUserDto userDto) {
        try {
            User user = userRepository.findByEmail(userDto.getEmail());
            if (user != null && user.getEmail().equals(userDto.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
            }
            User newUser = userMapper.toEntity(userDto);
            newUser.setPassword(authconfig.passwordEncoder().encode(newUser.getPassword()));
            userRepository.save(newUser);
            String token = jwtService.generateToken(newUser);
            return token;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public String loginUser(LoginUserDto userDto) {
        try {
            User user = userRepository.findByEmail(userDto.getEmail());
            Boolean isSamePassword = authconfig.passwordEncoder().matches(userDto.getPassword(), user.getPassword());
            if ((user != null) && !isSamePassword) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
            }
            String token = jwtService.generateToken(user);
            return token;
        } catch (java.lang.Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}

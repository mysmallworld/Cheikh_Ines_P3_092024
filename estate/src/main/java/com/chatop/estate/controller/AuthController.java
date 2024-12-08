package com.chatop.estate.controller;

import com.chatop.estate.dto.LoginUserDto;
import com.chatop.estate.dto.RegisterUserDto;
import com.chatop.estate.dto.UserResponseDto;
import com.chatop.estate.service.AuthService;
import com.chatop.estate.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterUserDto userDto){
        return authService.registerUser(userDto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUserDto userDto) {
        return authService.loginUser(userDto);
    }

    @GetMapping("/me")
    public UserResponseDto getUser(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.getUser(authorizationHeader);
    }
}

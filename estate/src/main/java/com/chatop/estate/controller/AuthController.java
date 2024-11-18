package com.chatop.estate.controller;

import com.chatop.estate.Dto.LoginUserDto;
import com.chatop.estate.Dto.RegisterUserDto;
import com.chatop.estate.Dto.UserResponseDto;
import com.chatop.estate.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    private final String api = "/api/auth";

    @PostMapping(api+"/register")
    public String registerUser(@RequestBody RegisterUserDto userDto){
        String registerUser = authService.registerUser(userDto);
        return registerUser;
    }

    @PostMapping(api+"/login")
    public String loginUser(@RequestBody LoginUserDto userDto) {
        String loginUser = authService.loginUser(userDto);
        return loginUser;
    }

    @GetMapping(api+"/me")
    public UserResponseDto getUser(@RequestHeader("Authorization") String authorizationHeader) {
        UserResponseDto getUser = authService.getUser(authorizationHeader);
        return getUser;
    }
}

package com.chatop.estate.controller;

import com.chatop.estate.Dto.UsersDto;
import com.chatop.estate.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    private final String api = "/api/auth";

    @PostMapping(api+"/register")
    public String registerUser(@RequestBody UsersDto userDto){
        String registerUser = userService.registerUser(userDto);
        return registerUser;
    }

    @PostMapping(api+"/login")
    public String loginUser(@RequestBody UsersDto userDto) {
        String loginUser = userService.loginUser(userDto);
        return loginUser;
    }

    @GetMapping(api+"/me")
    public String getUser() {
        String getUser = userService.getUser();
        return getUser;
    }

}

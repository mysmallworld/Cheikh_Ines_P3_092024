package com.chatop.estate.controller;

import com.chatop.estate.dto.LoginUserDto;
import com.chatop.estate.dto.RegisterUserDto;
import com.chatop.estate.dto.UserResponseDto;
import com.chatop.estate.service.AuthService;
import com.chatop.estate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Register a new user",
            description = "Allows a user to register."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token JWT"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterUserDto userDto){
        return authService.registerUser(userDto);
    }

    @Operation(
            summary = "Login a user",
            description = "Allows a user to login."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token JWT"),
            @ApiResponse(responseCode = "401", description = "Bad informations")
    })
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUserDto userDto) {
        return authService.loginUser(userDto);
    }

    @Operation(
            summary = "Get a user",
            description = "Allows a user to get his informations."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User informations"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/me")
    public UserResponseDto getUser(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.getUser(authorizationHeader);
    }
}

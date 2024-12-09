package com.chatop.estate.controller;

import com.chatop.estate.dto.LoginUserDto;
import com.chatop.estate.dto.RegisterUserDto;
import com.chatop.estate.dto.UserResponseDto;
import com.chatop.estate.service.AuthService;
import com.chatop.estate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDto userDto){
        String token = authService.registerUser(userDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
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
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDto userDto) {
        String token = authService.loginUser(userDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
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
    public ResponseEntity<UserResponseDto> getUser(@RequestHeader("Authorization") String authorizationHeader) {
        UserResponseDto userResponseDto = userService.getUser(authorizationHeader);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}

package com.chatop.estate.controller;

import com.chatop.estate.dto.LoginUserDto;
import com.chatop.estate.dto.AuthResponse;
import com.chatop.estate.dto.RegisterUserDto;
import com.chatop.estate.dto.UserResponse;
import com.chatop.estate.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
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
    public ResponseEntity<AuthResponse> registerUser(@Valid @RequestBody RegisterUserDto userDto){
        String token = authService.registerUser(userDto);
        AuthResponse registerResponse = AuthResponse.builder().token(token).build();
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
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
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginUserDto userDto) {
        String token = authService.loginUser(userDto);
        AuthResponse loginResponse = AuthResponse.builder().token(token).build();
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
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
    public ResponseEntity<UserResponse> getUser(@RequestHeader("Authorization") String authorizationHeader) {
        UserResponse userResponse = authService.getUser(authorizationHeader);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}

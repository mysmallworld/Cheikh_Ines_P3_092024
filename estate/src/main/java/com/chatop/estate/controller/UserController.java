package com.chatop.estate.controller;

import com.chatop.estate.dto.UserResponse;
import com.chatop.estate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get a user by id",
            description = "Allows a user to get his informations."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User informations"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer id) {
        UserResponse userResponseDto = userService.getUserById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}

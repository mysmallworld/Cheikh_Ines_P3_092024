package com.chatop.estate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUserDto {

    @NotNull()
    @Email(message = "Email must be valid")
    private String email;

    @NotNull()
    @Size(min = 8, max = 20, message = "Password must contain between 8 and 20 characters")
    private String password;
}

package com.chatop.estate.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public UserResponse(Integer id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created_at = createdAt;
        this.updated_at = updatedAt;
    }
}

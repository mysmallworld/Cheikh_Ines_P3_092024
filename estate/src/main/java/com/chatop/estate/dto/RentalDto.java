package com.chatop.estate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentalDto {

    @NotNull()
    private Integer id;

    @NotNull()
    private String name;

    @NotNull()
    private Double surface;

    @NotNull()
    private Double price;

    @NotNull()
    private String picture;

    @NotNull()
    private String description;

    @NotNull()
    private Integer ownerId;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

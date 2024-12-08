package com.chatop.estate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RentalDto {

    @NotNull()
    private UUID id;

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
    private UUID ownerId;
}

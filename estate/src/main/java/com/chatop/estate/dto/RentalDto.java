package com.chatop.estate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalDto {

    @NotNull(message = "id is required")
    private String id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "surface is required")
    private Double surface;

    @NotNull(message = "price is required")
    private Double price;

    @NotNull(message = "picture is required")
    private String picture;

    @NotNull(message = "description is required")
    private String description;

    @NotNull(message = "ownerId is required")
    private String ownerId;
}

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

    public @NotNull(message = "id is required") String getId() {
        return id;
    }

    public void setId(@NotNull(message = "id is required") String id) {
        this.id = id;
    }

    public @NotNull(message = "name is required") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "surface is required") Double getSurface() {
        return surface;
    }

    public void setSurface(@NotNull(message = "surface is required") Double surface) {
        this.surface = surface;
    }

    public @NotNull(message = "price is required") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "price is required") Double price) {
        this.price = price;
    }

    public @NotNull(message = "picture is required") String getPicture() {
        return picture;
    }

    public void setPicture(@NotNull(message = "picture is required") String picture) {
        this.picture = picture;
    }

    public @NotNull(message = "description is required") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "description is required") String description) {
        this.description = description;
    }

    public @NotNull(message = "ownerId is required") String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(@NotNull(message = "ownerId is required") String ownerId) {
        this.ownerId = ownerId;
    }
}

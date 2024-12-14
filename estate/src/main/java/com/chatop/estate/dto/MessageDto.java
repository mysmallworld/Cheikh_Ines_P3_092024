package com.chatop.estate.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    @NotNull
    private Integer user_id;

    @NotNull
    private String message;

    @NotNull
    private Integer rental_id;
}

package com.chatop.estate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    private Integer user_id;
    private String message;
    private Integer rental_id;
}

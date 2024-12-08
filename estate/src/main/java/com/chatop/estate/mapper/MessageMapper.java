package com.chatop.estate.mapper;

import com.chatop.estate.model.Message;
import com.chatop.estate.model.Rental;

import com.chatop.estate.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageMapper {

    public Message toEntity(String message, User user, Rental rental) {
        if (message == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();

        return Message.builder()
                .message(message)
                .user(user)
                .rental(rental)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}

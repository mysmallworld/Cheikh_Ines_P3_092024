package com.chatop.estate.service;

import com.chatop.estate.model.Message;
import com.chatop.estate.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String postMessage(String message){
        try {
            Message newMessage = new Message();
            newMessage.setMessage(message);
            LocalDateTime now = LocalDateTime.now();
            newMessage.setCreatedAt(now);
            newMessage.setUpdatedAt(now);
            messageRepository.save(newMessage);
            return "message: Message send with success";
        } catch (Exception e){
            throw new RuntimeException("An error occurred during message posting: " + e.getMessage());
        }
    }
}

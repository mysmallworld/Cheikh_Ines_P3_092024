package com.chatop.estate.service;

import com.chatop.estate.mapper.MessageMapper;
import com.chatop.estate.model.Message;
import com.chatop.estate.model.Rental;
import com.chatop.estate.model.User;
import com.chatop.estate.repository.MessageRepository;
import com.chatop.estate.repository.RentalRepository;
import com.chatop.estate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserService userService;

    @Autowired
    MessageMapper messageMapper;

    public String postMessage(String message, String authorizationHeader){
        try {
            String userEmail = userService.getUser(authorizationHeader).getEmail();
            User user = userRepository.findByEmail(userEmail);

            Rental rental = rentalRepository.findByUserId(user.getId());

            Message newMessage = messageMapper.toEntity(message, user, rental);
            messageRepository.save(newMessage);
            return "Message send with success";
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

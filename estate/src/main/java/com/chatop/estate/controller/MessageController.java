package com.chatop.estate.controller;

import com.chatop.estate.service.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping()
    public String postMessage(@RequestParam("message") String message){
        return messageService.postMessage(message);
    }
}

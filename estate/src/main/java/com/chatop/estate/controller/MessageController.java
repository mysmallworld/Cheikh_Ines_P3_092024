package com.chatop.estate.controller;

import com.chatop.estate.dto.MessageDto;
import com.chatop.estate.dto.SuccessResponse;
import com.chatop.estate.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(
            summary = "Send a new message",
            description = "Allows the user to post a new message associated with a rental or user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message send with success"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("")
    public ResponseEntity<SuccessResponse> postMessage(@RequestBody MessageDto messageDto) {
        String messageSuccess = messageService.postMessage(messageDto);
        SuccessResponse successResponse = SuccessResponse.builder().message(messageSuccess).build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}

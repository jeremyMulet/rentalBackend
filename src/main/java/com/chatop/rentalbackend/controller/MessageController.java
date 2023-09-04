package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.request.MessageRequest;
import com.chatop.rentalbackend.request.MessageResponse;
import com.chatop.rentalbackend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jérémy MULET on 21/08/2023.
 */
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Create a new message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message sent successfully", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token", content = @Content),
    })
    @PostMapping
    public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageRequest request) {
        messageService.createMessage(request);
        return ResponseEntity.ok(MessageResponse.builder().message("Message send with success").build());
    }
}

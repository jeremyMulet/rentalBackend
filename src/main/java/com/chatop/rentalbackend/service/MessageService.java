package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.model.Message;
import com.chatop.rentalbackend.repository.MessageRepository;
import com.chatop.rentalbackend.repository.UserRepository;
import com.chatop.rentalbackend.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Jérémy MULET on 21/08/2023.
 */
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final RentalService rentalService;

    public void createMessage(MessageRequest request) {
        Message msg = Message.builder()
                .message(request.getMessage())
                .user(userService.getUserById(request.getUser_id()).orElseThrow())
                .rental(rentalService.getRentalById(request.getRental_id()).orElseThrow())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        messageRepository.save(msg);
    }
}

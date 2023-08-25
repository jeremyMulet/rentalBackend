package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.exception.RentalNotFoundException;
import com.chatop.rentalbackend.exception.UserNotFoundException;
import com.chatop.rentalbackend.model.Message;
import com.chatop.rentalbackend.repository.MessageRepository;
import com.chatop.rentalbackend.repository.UserRepository;
import com.chatop.rentalbackend.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service class responsible for message-related operations.
 * <p>
 * @author Jérémy MULET
 * @since 21/08/2023
 */
@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalService rentalService;

    /**
     * Create a new message based on the provided request.
     *
     * @param request the message request data.
     */
    public void createMessage(MessageRequest request) {
        Message msg = Message.builder()
                .message(request.getMessage())
                .user(userRepository.getReferenceById(request.getUser_id())
                        .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.getUser_id())))
                .rental(rentalService.getRentalById(request.getRental_id())
                        .orElseThrow(() -> new RentalNotFoundException("Rental not found with id: " + request.getRental_id())))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        messageRepository.save(msg);
    }
}

package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.model.User;
import com.chatop.rentalbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Jérémy MULET on 21/08/2023.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }
}

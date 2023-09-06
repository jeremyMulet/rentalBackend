package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.exception.UserNotFoundException;
import com.chatop.rentalbackend.model.User;
import com.chatop.rentalbackend.repository.UserRepository;
import com.chatop.rentalbackend.request.UserResponse;
import com.chatop.rentalbackend.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for user-related operations.
 * <p>
 * @author Jérémy MULET
 * @since 21/08/2023
 */
@Service
@RequiredArgsConstructor
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Retrieve a user by its ID and convert it to a UserResponse DTO.
     *
     * @param id the ID of the user to retrieve.
     * @return a UserResponse DTO representing the user.
     * @throws UserNotFoundException if no user is found for the given ID.
     */
    public UserResponse getUserById(Long id) {
        User user = userRepository.getReferenceById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .id(id)
                .created_at(DateUtils.format(user.getCreatedAt()))
                .updated_at(DateUtils.format(user.getUpdatedAt()))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + username));
        return  User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}

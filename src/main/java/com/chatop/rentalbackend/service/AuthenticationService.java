package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.model.User;
import com.chatop.rentalbackend.repository.UserRepository;
import com.chatop.rentalbackend.request.AuthenticationRequest;
import com.chatop.rentalbackend.request.AuthenticationResponse;
import com.chatop.rentalbackend.request.RegisterRequest;
import com.chatop.rentalbackend.request.UserResponse;
import com.chatop.rentalbackend.utils.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service class responsible for handling user registration, authentication, and
 * retrieving details of the currently authenticated user.
 *
 * <p>
 * This service provides methods to register new users, authenticate users, and
 * get details about the user currently logged in.
 * </p>
 *
 * @author Jérémy MULET
 * @since 16/08/2023
 */

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Retrieves details about the currently authenticated user.
     *
     * @param request the current HTTP request.
     * @return the user response containing details about the current user or null if not authenticated.
     */
    public UserResponse currentUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            var user = userRepository.findByEmail(jwtService.extractUsername(token))
                    .orElseThrow();
            return UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .created_at(DateUtils.format(user.getCreatedAt()))
                    .updated_at(DateUtils.format(user.getUpdatedAt()))
                    .build();
        }
        return null;
    }
}

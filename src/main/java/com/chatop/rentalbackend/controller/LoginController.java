package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.request.AuthenticationRequest;
import com.chatop.rentalbackend.request.AuthenticationResponse;
import com.chatop.rentalbackend.request.RegisterRequest;
import com.chatop.rentalbackend.request.UserResponse;
import com.chatop.rentalbackend.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Jérémy MULET on 16/08/2023.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationService authService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered user", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content),
    })
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @Operation(summary = "Retrieve current authenticated user's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user details", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
    })

    @GetMapping("/me")
    public ResponseEntity<Optional<UserResponse>> me(HttpServletRequest request) {
        return ResponseEntity.ok(Optional.ofNullable(authService.currentUser(request)));
    }


}

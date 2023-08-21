package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.request.AuthenticationRequest;
import com.chatop.rentalbackend.request.AuthenticationResponse;
import com.chatop.rentalbackend.request.RegisterRequest;
import com.chatop.rentalbackend.request.UserResponse;
import com.chatop.rentalbackend.service.AuthenticationService;
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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<Optional<UserResponse>> me(HttpServletRequest request) {
        return ResponseEntity.ok(Optional.ofNullable(authService.currentUser(request)));
    }


}

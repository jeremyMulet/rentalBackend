package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.request.UserResponse;
import com.chatop.rentalbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Jérémy MULET on 25/08/2023.
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> me(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}

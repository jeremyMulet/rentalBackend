package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.request.UserResponse;
import com.chatop.rentalbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Retrieve a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User fetched successfully", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> me(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}

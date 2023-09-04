package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.model.Rental;
import com.chatop.rentalbackend.request.FormDataRental;
import com.chatop.rentalbackend.request.MessageResponse;
import com.chatop.rentalbackend.request.RentalResponse;
import com.chatop.rentalbackend.request.RentalsResponse;
import com.chatop.rentalbackend.service.RentalService;
import com.chatop.rentalbackend.utils.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Jérémy MULET on 19/08/2023.
 */
@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @Operation(summary = "Retrieve all rentals")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rentals fetched successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden - Access denied", content = @Content),
    })
    @GetMapping
    public ResponseEntity<RentalsResponse> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @Operation(summary = "Retrieve a rental by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental fetched successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token", content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRental(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id).orElseThrow();
        RentalResponse response = RentalResponse.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture(rental.getPicture())
                .description(rental.getDescription())
                .owner_id(rental.getOwner().getId())
                .created_at(DateUtils.format(rental.getCreatedAt()))
                .updated_at(DateUtils.format(rental.getUpdatedAt()))
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a new rental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token", content = @Content),
    })
    @PostMapping
    public ResponseEntity<MessageResponse> createRental(HttpServletRequest request, FormDataRental formData) {
        if(rentalService.createRental(request, formData)) {
            return ResponseEntity.ok(MessageResponse.builder().message("Rental created!").build());
        } else {
            return ResponseEntity.ok(MessageResponse.builder().message("Cannot create rental...").build());
        }

    }

    @Operation(summary = "Update an existing rental by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token", content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateRental(@PathVariable Long id, FormDataRental updatedRental) {
        return rentalService.getRentalById(id)
                .map(rental -> {
                    if (updatedRental.getName() != null) {
                        rental.setName(updatedRental.getName());
                    }
                    if (updatedRental.getSurface() != null) {
                        rental.setSurface(updatedRental.getSurface());
                    }
                    if (updatedRental.getPrice() != null) {
                        rental.setPrice(updatedRental.getPrice());
                    }
                    if (updatedRental.getDescription() != null) {
                        rental.setDescription(updatedRental.getDescription());
                    }
                    rentalService.saveRental(rental);
                    return ResponseEntity.ok(MessageResponse.builder().message("Rental Updated!").build());
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
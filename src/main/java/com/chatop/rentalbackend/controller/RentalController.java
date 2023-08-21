package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.model.Rental;
import com.chatop.rentalbackend.request.FormDataRental;
import com.chatop.rentalbackend.request.RentalResponse;
import com.chatop.rentalbackend.service.RentalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jérémy MULET on 19/08/2023.
 */
@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

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
                .created_at(rental.getCreatedAt())
                .updated_at(rental.getUpdatedAt())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> createRental(HttpServletRequest request, FormDataRental formData) {
        if(rentalService.createRental(request, formData)) {
            return ResponseEntity.ok("Rental created!");
        } else {
            return ResponseEntity.ok("Cannot create rental...");
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateRental(@PathVariable Long id, @RequestBody Rental updatedRental) {
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

                    Rental savedRental = rentalService.saveRental(rental);
                    return ResponseEntity.ok("Rental Updated!"); // renvoie le rental mis à jour avec un statut 200 OK
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // renvoie un statut 404 Not Found si le rental n'est pas trouvé
    }

}
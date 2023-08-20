package com.chatop.rentalbackend.controller;

import com.chatop.rentalbackend.model.Rental;
import com.chatop.rentalbackend.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Rental getRental(@PathVariable Long id) {
        return rentalService.getRentalById(id).orElse(null);
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
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
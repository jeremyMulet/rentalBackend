package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.model.Rental;
import com.chatop.rentalbackend.repository.RentalRepository;
import com.chatop.rentalbackend.repository.UserRepository;
import com.chatop.rentalbackend.request.FormDataRental;
import com.chatop.rentalbackend.request.RentalResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jérémy MULET on 19/08/2023.
 */
@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public List<RentalResponse> getAllRentals() {
        var rentals = rentalRepository.findAll();
        List<RentalResponse> response = new ArrayList<>();
        rentals.forEach(rental -> {
            RentalResponse rentalResponse = RentalResponse.builder()
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
            response.add(rentalResponse);
        });
        return response;
    }

    public Optional<Rental> getRentalById(Long id) {
        var rental = rentalRepository.findById(id);
        System.out.println(rental);
        return rental;
    }

    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public Boolean createRental(HttpServletRequest request, FormDataRental formData) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            var user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow();
            var rental = Rental.builder()
                    .name(formData.getName())
                    .surface(formData.getSurface())
                    .price(formData.getPrice())
                    .picture(formData.getPicture())
                    .owner(user)
                    .description(formData.getDescription())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            rentalRepository.save(rental);
            return true;
        }
        return false;

    }

}

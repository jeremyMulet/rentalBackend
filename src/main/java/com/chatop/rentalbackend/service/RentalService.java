package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.model.Rental;
import com.chatop.rentalbackend.repository.RentalRepository;
import com.chatop.rentalbackend.repository.UserRepository;
import com.chatop.rentalbackend.request.FormDataRental;
import com.chatop.rentalbackend.request.UserResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Boolean createRental(HttpServletRequest request, FormDataRental formData) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            var user =  userRepository.findByEmail(jwtService.extractUsername(token))
                    .orElseThrow();
            var rental = Rental.builder()
                    .name(formData.getName())
                    .surface(new BigDecimal(formData.getSurface()) )
                    .price(new BigDecimal(formData.getPrice()))
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

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

}

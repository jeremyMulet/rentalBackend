package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.model.Rental;
import com.chatop.rentalbackend.repository.RentalRepository;
import com.chatop.rentalbackend.repository.UserRepository;
import com.chatop.rentalbackend.request.FormDataRental;
import com.chatop.rentalbackend.request.RentalResponse;
import com.chatop.rentalbackend.request.RentalsResponse;
import com.chatop.rentalbackend.utils.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Service for handling CRUD operations related to rentals.
 * <p>
 * @author Jérémy MULET
 * @since 19/08/2023
 */
@Service
@RequiredArgsConstructor
public class RentalService {

    private static final String UPLOAD_DIR = "src/main/resources/pictures/";

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Fetch all rentals.
     *
     * @return A response containing all rentals.
     */
    public RentalsResponse getAllRentals() {
        var rentals = rentalRepository.findAll();
        ArrayList<RentalResponse> response = new ArrayList<>();
        rentals.forEach(rental -> {
            RentalResponse rentalResponse = RentalResponse.builder()
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
            response.add(rentalResponse);
        });
        return RentalsResponse.builder()
                .rentals(response)
                .build();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    /**
     * Create a new rental.
     *
     * @param request  the HTTP request to extract JWT token.
     * @param formData the form data containing rental details.
     * @return true if rental is successfully created, false otherwise.
     */
    public Boolean createRental(HttpServletRequest request, FormDataRental formData) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            var user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow();
            String imagePath = storeImage(formData.getPicture());
            var rental = Rental.builder()
                    .name(formData.getName())
                    .surface(formData.getSurface())
                    .price(formData.getPrice())
                    .picture("http://localhost:8080/api/pictures/"+formData.getPicture().getOriginalFilename())
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

    /**
     * Store an image and return its path.
     *
     * @param imageFile the image to be stored.
     * @return the path where the image is stored.
     */
    private String storeImage(MultipartFile imageFile) {
        try {
            Path root = Paths.get(UPLOAD_DIR);
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }
            Path resolve = root.resolve(imageFile.getOriginalFilename());
            imageFile.transferTo(resolve);
            return resolve.toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not store the image. Error: " + e.getMessage());
        }
    }

}

package com.chatop.rentalbackend.service;

import com.chatop.rentalbackend.model.Rental;
import com.chatop.rentalbackend.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jérémy MULET on 19/08/2023.
 */
@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

}

package com.chatop.rentalbackend.repository;

import com.chatop.rentalbackend.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and manipulating Rental entities in the database.
 * <p>
 * @author Jérémy MULET
 * @since 19/08/2023
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

}
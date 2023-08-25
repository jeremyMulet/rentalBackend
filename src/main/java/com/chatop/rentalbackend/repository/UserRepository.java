package com.chatop.rentalbackend.repository;

import com.chatop.rentalbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing and manipulating User entities in the database.
 * <p>
 * @author Jérémy MULET
 * @since 16/08/2023
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> getReferenceById(Long id);
}

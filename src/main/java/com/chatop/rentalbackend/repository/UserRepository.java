package com.chatop.rentalbackend.repository;

import com.chatop.rentalbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jérémy MULET on 16/08/2023.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> getReferenceById(Long id);
}

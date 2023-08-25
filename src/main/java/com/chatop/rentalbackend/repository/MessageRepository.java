package com.chatop.rentalbackend.repository;

import com.chatop.rentalbackend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and manipulating Message entities in the database.
 * <p>
 * @author Jérémy MULET
 * @since 21/08/2023
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}

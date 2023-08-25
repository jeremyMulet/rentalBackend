package com.chatop.rentalbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a Message entity in the system.
 * Each message is associated with a specific Rental and a User.
 * <p>
 * @author Jérémy MULET
 * @since 19/08/2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MESSAGES", indexes = {
        @Index(name = "idx_users_id", unique = true, columnList = "user_id"),
        @Index(name = "idx_rental_id", unique = true, columnList = "rental_id"),
})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The rental to which this message is associated
    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    // The user who sent this message
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 2000)
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}

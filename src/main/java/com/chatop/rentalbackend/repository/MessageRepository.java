package com.chatop.rentalbackend.repository;

import com.chatop.rentalbackend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Jérémy MULET on 21/08/2023.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}

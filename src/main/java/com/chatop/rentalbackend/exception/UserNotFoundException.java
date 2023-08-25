package com.chatop.rentalbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when a user entity is not found in the database.
 * It results in an HTTP 404 (Not Found) response when used in a controller.
 * <p>
 * @author Jérémy MULET
 * @since 25/08/2023
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
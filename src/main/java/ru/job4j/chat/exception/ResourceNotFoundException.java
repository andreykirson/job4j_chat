package ru.job4j.chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Andrey
 * @version 1
 * @date 2/21/2021
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found!");
    }
}
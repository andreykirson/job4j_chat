package ru.job4j.chat.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Andrey
 * @version 1
 * @date 2/25/2021
 */

public class JwtAuthException extends AuthenticationException {

    public JwtAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthException(String msg) {
        super(msg);
    }
}

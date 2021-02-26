package ru.job4j.chat.dto;

import lombok.Data;

/**
 * @author Andrey
 * @version 1
 * @date 2/25/2021
 */

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}

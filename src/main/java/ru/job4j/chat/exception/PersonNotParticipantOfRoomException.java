package ru.job4j.chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Andrey
 * @version 1
 * @date 2/22/2021
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PersonNotParticipantOfRoomException extends RuntimeException {
    public PersonNotParticipantOfRoomException() {
        super("Join to this room before send a message!");
    }
}
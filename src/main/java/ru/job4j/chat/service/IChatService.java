package ru.job4j.chat.service;

import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;

/**
 * @author Andrey
 * @version 1
 * @date 2/23/2021
 */

public interface IChatService {
    boolean sendMessage(int roomId, int authorId, Message message);
}

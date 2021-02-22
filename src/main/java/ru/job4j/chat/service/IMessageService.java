package ru.job4j.chat.service;

import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @date 2/20/2021
 */

public interface IMessageService {

    List<Message> findMsgByPerson(Person person);

    List<Message> findMsgInRoom(Room room);

    List<Message> findMsgByPersonInRoom(Person person, Room room);

    List<Message> findMsgByWord(String str);

    List<Message> findMsgInRoomByWord(Room room, String str);

    Message saveOrUpdate(Message message);

    void deleteMsgByAuthorAndRoomAndId(Person person, Room room, int id);

    void deleteAllMsgInRoom(Room room);
}

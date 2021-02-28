package ru.job4j.chat.service;

import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/21/2021
 */

public interface IRoomService {
    Room saveOrUpdate(Room room);
    void delete(Room room);
    List<Room> findAll();
    List<Room> findRoomByTitle(String title);
    Optional<Room> findRoomById(int id);
    int joinToRoom(Person person, Room room);
    void leaveRoom(Room room, Person person);
    Person findParticipant(Room room, Person person);
}

package ru.job4j.chat.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @date 2/19/2021
 */

@Repository
public interface MsgRepository extends CrudRepository<Message, Integer> {
    List<Message> findMessagesByAuthor(Person person);
    List<Message> findMessagesByRoom(Room room);
    List<Message> findMessagesByAuthorAndRoom(Person person, Room room);
    List<Message> findMessagesByContentContains(String str);
    List<Message> findMessagesByRoomAndContentContains(Room room, String str);
    void deleteByAuthorAndRoomAndId(Person person, Room room, int id);
    void deleteAllByRoom(Room room);
    Message save(Message message);

}
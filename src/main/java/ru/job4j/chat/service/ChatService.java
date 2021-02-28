package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/23/2021
 */

@Service
public class ChatService implements IChatService {

    @Autowired
    RoomService roomService;

    @Autowired
    MessageService messageService;

    @Autowired
    PersonService personService;

    @Override
    public boolean sendMessage(int roomId, int authorId, Message message) {
        Optional<Person> person = personService.findPersonById(authorId);
        if (person.isEmpty()) {
            return false;
        }
        Optional<Room> room = roomService.findRoomById(roomId);
        if (room.isEmpty()) {
            return false;
        }
        Optional<Person> participant = roomService.findParticipant(room.get(), person.get());
        if (participant.isEmpty()) {
            return false;
        }
        message.setAuthor(person.get());
        message.setRoom(room.get());
        return messageService.saveOrUpdate(message) != null;
    }
}

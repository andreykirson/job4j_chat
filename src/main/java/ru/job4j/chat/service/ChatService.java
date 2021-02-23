package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.exception.PersonNotParticipantOfRoomException;
import ru.job4j.chat.exception.ResourceNotFoundException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;

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
        Person person = personService.findPersonById(authorId).orElseThrow(ResourceNotFoundException::new);
        Room room = roomService.findRoomById(roomId).orElseThrow(ResourceNotFoundException::new);
        if (roomService.findParticipant(room, person) != 0) {
            message.setAuthor(person);
            message.setRoom(room);
        }
        return messageService.saveOrUpdate(message) != null;
    }
}

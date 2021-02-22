package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exception.PersonNotParticipantOfRoomException;
import ru.job4j.chat.exception.ResourceNotFoundException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.MessageService;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.service.RoomService;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/21/2021
 */
@RestController
@RequestMapping("/person/{personId}/room/{roomId}/message")
public class MessageControl {
    @Autowired
    private PersonService personService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MessageService messageService;


    @PostMapping
    public Message createMsg(@PathVariable int personId, @PathVariable int roomId, @RequestBody Message message) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        Room room = roomService.findRoomById(roomId).orElseThrow(ResourceNotFoundException::new);
        Optional<Person> participant = Optional.of((Person) room.getParticipants().stream().filter(p -> p.getId() == person.getId()));
        if (participant.isPresent()) {
            message.setAuthor(person);
            message.setRoom(room);
        } else {
           throw new PersonNotParticipantOfRoomException();
        }
        return messageService.saveOrUpdate(message);
    }

    @GetMapping
    public List<Message> getAllMsgInRoom(@PathVariable int roomId) {
        Room room = roomService.findRoomById(roomId).orElseThrow(ResourceNotFoundException::new);
        return messageService.findMsgInRoom(room);
    }

    @DeleteMapping("/{personId}/{roomId}/{msgId}")
    public void deleteMsgByAuthorAndRoomAndMsgId(@PathVariable int personId, @PathVariable int roomId, @PathVariable int msgId) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        Room room = roomService.findRoomById(roomId).orElseThrow(ResourceNotFoundException::new);
        messageService.deleteMsgByAuthorAndRoomAndId(person, room, msgId);
    }

}

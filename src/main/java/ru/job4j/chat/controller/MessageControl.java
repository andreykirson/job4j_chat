package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exception.ResourceNotFoundException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.MessageService;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.service.RoomService;
import java.util.List;

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

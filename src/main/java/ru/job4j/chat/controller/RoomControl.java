package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exception.ResourceNotFoundException;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.service.RoomService;
import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @date 2/21/2021
 */

@RestController
@RequestMapping("/person/{personId}/room")
public class RoomControl {

    @Autowired
    private RoomService roomService;

    @Autowired
    private PersonService personService;

    @PostMapping("/{personId}/create")
    public Room createRoom(@PathVariable int personId, @RequestBody Room room) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        room.setCreator(person);
        roomService.saveOrUpdate(room);
        roomService.joinToRoom(person, room);
        return room;
    }

    @PostMapping("/person/{personId}/room/join")
    public Room joinToRoom(@PathVariable int personId, @RequestBody Room room) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        roomService.joinToRoom(person, room);
        return room;
    }

    @DeleteMapping
    public void leaveRoom(@PathVariable int personId, @RequestBody Room room) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        roomService.leaveRoom(room, person);
    }


    @GetMapping("/rooms")
    public List<Room> getAllRoom() {
        return roomService.findAll();
    }

    @DeleteMapping("/person/{personId}/room/delete")
    public void deleteRoom(@PathVariable int personId, @RequestBody Room room) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        room.setCreator(person);
        roomService.delete(room);
    }

}

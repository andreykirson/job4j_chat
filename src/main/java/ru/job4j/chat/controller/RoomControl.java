package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Room createRoom(@PathVariable int personId, @RequestBody Room room) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        room.setCreator(person);
        roomService.joinToRoom(person, room);
        return roomService.saveOrUpdate(room);
    }

    @PostMapping
    public void joinToRoom(@PathVariable int personId, @RequestBody Room room) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        roomService.joinToRoom(person, room);
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

    @DeleteMapping
    public void deleteRoom(@PathVariable int personId, @RequestBody Room room) {
        Person person = personService.findPersonById(personId).orElseThrow(ResourceNotFoundException::new);
        room.setCreator(person);
        roomService.delete(room);
    }



}

package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repo.RoomRepository;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/21/2021
 */

@Service
public class RoomService implements IRoomService{

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    MessageService messageService;

    @Autowired
    PersonService personService;


    @Override
    public Room saveOrUpdate(Room room) {
        return roomRepository.save(room);
    }

    @Override
    @Transactional
    public void delete(Room room) {
        messageService.deleteAllMsgInRoom(room);
        roomRepository.removeAllParticipantsInRoom(room);
        roomRepository.delete(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findRoomByTitle(String title) {
        return roomRepository.findAllByTitle(title);
    }

    @Override
    public Optional<Room> findRoomById(int id) {
        return roomRepository.findRoomById(id);
    }

    @Override
    @Transactional
    public int joinToRoom(Person person, Room room) {
       return roomRepository.addParticipant(person, room);
    }

    @Override
    public void leaveRoom(Room room, Person person) {
        roomRepository.removeParticipantsByPerson(room, person);
    }

    @Override
    public Person findParticipant(Room room, Person person) {
        return roomRepository.findByParticipant(room, person);
    }
}

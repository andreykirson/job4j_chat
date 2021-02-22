package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repo.MsgRepository;
import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @date 2/20/2021
 */


@Service
public class MessageService implements IMessageService {

    @Autowired
    MsgRepository msgRepository;

    @Override
    public List<Message> findMsgByPerson(Person person) {
        return msgRepository.findMessagesByAuthor(person);
    }

    @Override
    public List<Message> findMsgInRoom(Room room) {
        return msgRepository.findMessagesByRoom(room);
    }

    @Override
    public List<Message> findMsgByPersonInRoom(Person person, Room room) {
        return msgRepository.findMessagesByAuthorAndRoom(person, room);
    }

    @Override
    public List<Message> findMsgByWord(String str) {
        return msgRepository.findMessagesByContentContains(str);
    }

    @Override
    public List<Message> findMsgInRoomByWord(Room room, String str) {
        return msgRepository.findMessagesByRoomAndContentContains(room, str);
    }

    @Override
    public Message saveOrUpdate(Message message) {
        return msgRepository.save(message);
    }

    @Override
    @Transactional
    public void deleteMsgByAuthorAndRoomAndId(Person person, Room room, int id) {
        msgRepository.deleteByAuthorAndRoomAndId(person, room, id);
    }

    @Override
    public void deleteAllMsgInRoom(Room room) {
        msgRepository.deleteAllByRoom(room);
    }
}

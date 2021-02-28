package ru.job4j.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repo.RoleRepository;
import ru.job4j.chat.repo.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ChatServiceTest {

    @MockBean
    private RoomService roomService;

    @MockBean
    private PersonService personService;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private ChatService chatService;

    @MockBean
    private MessageService messageService;


    @Test
    public void whenSendMessage() {
        Person p = new Person();
        p.setId(1);
        p.setName("Ivan");
        List<Person> personList = new ArrayList<>();
        personList.add(p);
        Room r = new Room();
        r.setId(1);
        r.setCreator(p);
        r.setParticipants(personList);
        Message msg = new Message();
        msg.setId(1);
        msg.setAuthor(p);
        msg.setRoom(r);
        Mockito.when(personService.findPersonById(p.getId())).thenReturn(Optional.of(p));
        log.info("The found person is : {}", personService.findPersonById(p.getId()));
        Mockito.when(roomService.findRoomById(r.getId())).thenReturn(Optional.of(r));
        log.info("The found room is : {}", roomService.findRoomById(r.getId()));
        Mockito.when(roomService.joinToRoom(p, r)).thenReturn(1);
        Mockito.when(roomService.findParticipant(r, p)).thenReturn(Optional.of(p));
        log.info("The found participant is : {}", roomService.findParticipant(r, p));
        Mockito.when(messageService.saveOrUpdate(msg)).thenReturn(msg);
        assertTrue(chatService.sendMessage(1,1, msg));
    }

    @Test
    public void whenCreateRoom() {
        Person p = new Person();
        p.setId(1);
        List<Person> personList = new ArrayList<>();
        personList.add(p);
        Room r = new Room();
        r.setId(1);
        r.setCreator(p);
        Mockito.when(roomService.findRoomById(r.getId())).thenReturn(Optional.of(r));
        assertEquals(roomService.findRoomById(r.getId()).get(), r);
    }



//    @Test
//    public void whenSendMessageWithoutJoinRoom() {
//        Person p = new Person();
//        p.setId(1);
//        Room r = new Room();
//        r.setId(1);
//        r.setCreator(p);
//        Message msg = new Message();
//        msg.setId(1);
//        msg.setAuthor(p);
//        msg.setRoom(r);
//        Mockito.when(personService.findPersonById(p.getId())).thenReturn(Optional.of(p));
//        Mockito.when(roomService.findRoomById(r.getId())).thenReturn(Optional.of(r));
//        Mockito.when(roomService.joinToRoom(p, r)).thenReturn(1);
//        Mockito.when(roomService.findParticipant(r, p)).thenReturn(0);
//        Mockito.when(messageService.saveOrUpdate(msg)).thenReturn(msg);
//        assertFalse(chatService.sendMessage(r.getId(), p.getId(), msg));
//    }
}
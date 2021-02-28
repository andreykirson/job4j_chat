package ru.job4j.chat.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ChatServiceTest {

    @MockBean
    private RoomService roomService;

    @MockBean
    private PersonService personService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;


    @Test
    public void whenSendMessage() {
        Person p = new Person();
        p.setId(1);
        List<Person> personList = new ArrayList<>();
        personList.add(p);
        Room r = new Room();
        r.setId(1);
        r.setCreator(p);
        Message msg = new Message();
        msg.setId(1);
        msg.setAuthor(p);
        msg.setRoom(r);
        Mockito.when(personService.findPersonById(p.getId())).thenReturn(Optional.of(p));
        Mockito.when(roomService.findRoomById(r.getId())).thenReturn(Optional.of(r));
        Mockito.when(roomService.joinToRoom(p, r)).thenReturn(1);
        Mockito.when(roomService.findParticipant(r, p)).thenReturn(1);
        Mockito.when(messageService.saveOrUpdate(msg)).thenReturn(msg);
        assertTrue(chatService.sendMessage(r.getId(), p.getId(), msg));
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
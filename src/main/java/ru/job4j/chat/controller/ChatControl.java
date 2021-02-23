package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.service.ChatService;

/**
 * @author Andrey
 * @version 1
 * @date 2/23/2021
 */
@RestController
@RequestMapping("/person/{personId}/room/{roomId}/chat")
public class ChatControl {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public boolean sendMessage(@PathVariable int personId, @PathVariable int roomId, @RequestBody Message message) {
        return chatService.sendMessage(roomId, personId, message);
    }
}

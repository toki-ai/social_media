package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Message;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.MessageService;
import com.toki.socialmedia.service.MessageServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Operation(summary = "Get message by chat")
    @GetMapping("/{chatId}")
    public List<Message> getMessageByChat(@PathVariable UUID chatId) throws Exception {
        return messageServiceImpl.getMessageByChat(chatId);
    }

    @Operation(summary = "Create message")
    @PostMapping("/create/{chatId}")
    public Message createMessage(@RequestBody Message message, @PathVariable UUID chatId, @RequestHeader("Authorization") String token) throws Exception {
        User user = userServiceImpl.getUserByToken(token);
        Message newMessage = messageServiceImpl.createMessage(user, message, chatId);
        return newMessage;
    }
}

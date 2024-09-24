package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Chat;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.request.ChatRequest;
import com.toki.socialmedia.service.ChatServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatServiceImpl chatServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Operation(summary = "Create chat")
    @PostMapping("/create")
    public Chat createChat(@RequestBody ChatRequest chatRequest, @RequestHeader("Authorization") String token) throws Exception {
        User user = userServiceImpl.getUserByToken(token);
        User resUser = userServiceImpl.getUserById(chatRequest.getResUserId());
        Chat chat = chatServiceImpl.createChat(user, resUser);
        return chat;
    }

    @Operation(summary = "Get chats by user")
    @GetMapping("/userChats")
    public List<Chat> getUserChats(@RequestHeader("Authorization") String token) throws Exception {
        User user = userServiceImpl.getUserByToken(token);
        List<Chat> chats = chatServiceImpl.getChatByUserId(user.getId());
        return chats;
    }
}

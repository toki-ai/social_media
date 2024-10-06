package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Chat;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Chat> getChatByUserId(UUID userId) throws Exception {
        List<Chat> list = new ArrayList<>();
        User user = userServiceImpl.getUserById(userId);
        list = chatRepository.findChatByUsers(user);
        return list;
    }

    @Override
    public Chat getChatByTwoUserId(UUID userId1, UUID userId2) throws Exception {
        User user1 = userServiceImpl.getUserById(userId1);
        User user2 = userServiceImpl.getUserById(userId2);
        Chat chat = chatRepository.findChatByUsers(user1, user2);
        return chat;
    }

    @Override
    public Chat getChatById(UUID chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if (chat.isPresent()) {
            return chat.get();
        }
        throw new Exception("Chat not found");
    }

    @Override
    public Chat createChat(User reqUser, User resUser) {
        Chat isExit = chatRepository.findChatByUsers(reqUser, resUser);
        if (isExit != null) {
            return isExit;
        }
        Chat newChat = new Chat();
        newChat.setTimeStamps(LocalDateTime.now());
        newChat.setUsers(new ArrayList<>());
        newChat.getUsers().add(reqUser);
        newChat.getUsers().add(resUser);
        Chat saveChat = chatRepository.save(newChat);
        return saveChat;
    }
}

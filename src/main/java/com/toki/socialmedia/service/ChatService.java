package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Chat;
import com.toki.socialmedia.model.User;

import java.util.List;
import java.util.UUID;

public interface ChatService {
    List<Chat> getChatByUserId(UUID userId) throws Exception;
    Chat getChatById(UUID chatId) throws Exception;
    Chat createChat(User reqUser, User resUser);
}

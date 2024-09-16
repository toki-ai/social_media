package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Message;
import com.toki.socialmedia.model.User;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    List<Message> getMessageByChat(UUID chatId) throws Exception;
    Message createMessage(User user, Message message, UUID chatId) throws Exception;
}

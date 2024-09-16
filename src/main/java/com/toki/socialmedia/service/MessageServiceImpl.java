package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Chat;
import com.toki.socialmedia.model.Message;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.ChatRepository;
import com.toki.socialmedia.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatServiceImpl chatServiceImpl;

    @Override
    public List<Message> getMessageByChat(UUID chatId) throws Exception {
        List<Message> list = new ArrayList<>();
        Chat chat = chatServiceImpl.getChatById(chatId);
        list = messageRepository.findMessageByChat(chat);
        return list;
    }

    @Override
    public Message createMessage(User user, Message message, UUID chatId) throws Exception {
        Chat chat = chatServiceImpl.getChatById(chatId);
        Message newMessage = new Message();
        newMessage.setChat(chat);
        if(message.getContent() != null) {
            newMessage.setContent(message.getContent());
        }
        if(message.getImage() != null) {
            newMessage.setImage(message.getImage());
        }
        newMessage.setSender(user);
        chat.getMessages().add(newMessage);
        chatRepository.save(chat);
        Message savedMessage = messageRepository.save(newMessage);
        return savedMessage;
    }
}

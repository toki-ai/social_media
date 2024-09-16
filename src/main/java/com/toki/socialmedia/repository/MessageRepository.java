package com.toki.socialmedia.repository;

import com.toki.socialmedia.model.Chat;
import com.toki.socialmedia.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findMessageByChat(Chat chat);

}

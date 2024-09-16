package com.toki.socialmedia.repository;

import com.toki.socialmedia.model.Chat;
import com.toki.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    List<Chat> findChatByUsers(User user);

    @Query("SELECT c FROM Chat c WHERE :reqUser member of c.users AND :resUser member of c.users")
    Chat findChatByUsers(@Param("reqUser") User userId, @Param("resUser") User userId2);
}

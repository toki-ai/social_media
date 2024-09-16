package com.toki.socialmedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String chatName;
    private String chatImage;
    @ManyToMany
    private List<User> users;
    private LocalDateTime timeStamps;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages;
}

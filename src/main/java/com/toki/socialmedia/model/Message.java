package com.toki.socialmedia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.toki.socialmedia.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String content;
    private String image;
    @ManyToOne
    private User sender;
    @ManyToOne
    @JsonIgnore
    private Chat chat;
    private LocalDateTime timeStamps;
}

package com.toki.socialmedia.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String caption;
    private String image;
    private String video;
    @ManyToOne
    private User user;
    private LocalDateTime date;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<User> liked;

}



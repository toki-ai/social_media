package com.toki.socialmedia.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String caption;
    private String image;
    private String video;

    @ManyToOne
    //@JsonIgnore : don't get json in response if it too overload
    private User user;
    private LocalDateTime date;

    @ManyToMany
    private List<User> liked;

    public Post() {
    }

    public Post(String caption, String image, String video, User user, LocalDateTime date, List<User> liked) {
        this.caption = caption;
        this.image = image;
        this.video = video;
        this.user = user;
        this.date = date;
        this.liked = liked;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }
}



package com.toki.socialmedia.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;
    @ElementCollection
    private List<UUID> following;
    @ElementCollection
    private List<UUID> followers;
    @ManyToMany
    private List<Post> saved;

    public List<Post> getSaved() {
        return saved;
    }

    public void setSaved(List<Post> saved) {
        this.saved = saved;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(List<Post> saved, Role role, String password, String lastname, UUID id, String gender, List<UUID> following, List<UUID> followers, String firstname, String email) {
        this.saved = saved;
        this.role = role;
        this.password = password;
        this.lastname = lastname;
        this.id = id;
        this.gender = gender;
        this.following = following;
        this.followers = followers;
        this.firstname = firstname;
        this.email = email;
    }

    public User(){}

    public List<UUID> getFollowing() {
        return following;
    }

    public void setFollowing(List<UUID> following) {
        this.following = following;
    }

    public List<UUID> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UUID> followers) {
        this.followers = followers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

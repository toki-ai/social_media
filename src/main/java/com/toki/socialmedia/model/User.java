package com.toki.socialmedia.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String gender;
    private List<Integer> following;
    private List<Integer> followers;
    private List<Post> saved;

    public List<Post> getSaved() {
        return saved;
    }

    public void setSaved(List<Post> saved) {
        this.saved = saved;
    }

    public User(String email, String firstname, List<Integer> followers, List<Integer> following, String gender, Integer id, String lastname, String password, List<Post> saved) {
        this.email = email;
        this.firstname = firstname;
        this.followers = followers;
        this.following = following;
        this.gender = gender;
        this.id = id;
        this.lastname = lastname;
        this.password = password;
        this.saved = saved;
    }

    public User(){}

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

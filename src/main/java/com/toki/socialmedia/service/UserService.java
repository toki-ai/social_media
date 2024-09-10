package com.toki.socialmedia.service;

import com.toki.socialmedia.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public User registerUser (User user);

    public List<User> findAllUser();

    public User findUserById (UUID userId) throws Exception;

    public User findUserByEmail (String userEmail) throws Exception;

    public User followUser(UUID userId1, UUID userId2) throws Exception;

    public User updateUser(UUID userId, User user) throws Exception;

    public List<User> searchUser(String query);

}

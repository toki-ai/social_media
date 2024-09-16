package com.toki.socialmedia.service;

import com.toki.socialmedia.exception.UserException;
import com.toki.socialmedia.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<User> getAllUser();

    public User getUserById(UUID userId) throws UserException;

    public User followUser(UUID userId1, UUID userId2) throws UserException;

    public User updateUser(UUID userId, User user) throws UserException;

    public List<User> searchUser(String query);

    public User getUserByToken(String token);

}

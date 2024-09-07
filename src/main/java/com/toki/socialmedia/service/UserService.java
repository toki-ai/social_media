package com.toki.socialmedia.service;

import com.toki.socialmedia.model.User;

import java.util.List;

public interface UserService {

    public User registerUser (User user);

    public User findUserById (Integer userId) throws Exception;

    public User findUserByEmail (String userEmail) throws Exception;

    public User followUser(Integer userId1, Integer userId2) throws Exception;

    public User updateUser(Integer userId, User user) throws Exception;

    public List<User> searchUser(String query);

}

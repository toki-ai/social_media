package com.toki.socialmedia.service;

import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());
        User saveUser = userRepository.save(newUser);
        return saveUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("User with id: " + userId + " not found");
    }

    @Override
    public User findUserByEmail(String userEmail) throws Exception {
        return null;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user2 = findUserById(userId2);
        User user1 = findUserById(userId1);
        if (user1.getFollowing() == null) {
            user1.setFollowing(new ArrayList<>());
        }
        if (user2.getFollowers() == null) {
            user2.setFollowers(new ArrayList<>());
        }
        user1.getFollowing().add(userId2);
        user2.getFollowers().add(userId1);
        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(Integer userId, User user) throws Exception {
        Optional<User> updatedUser = userRepository.findById(userId);
        User changeUser = null;
        if (updatedUser.isPresent()) {
            changeUser = updatedUser.get();
            if (user.getFirstname() != null) {
                changeUser.setFirstname(user.getFirstname());
            }
            if (user.getLastname() != null) {
                changeUser.setLastname(user.getLastname());
            }
            if (user.getPassword() != null) {
                changeUser.setPassword(user.getPassword());
            }
            if (user.getEmail() != null) {
                changeUser.setEmail(user.getEmail());
            }
            User afterUpdate = userRepository.save(changeUser);
            return afterUpdate;
        }
        throw new Exception("User with id: " + userId + " not found");
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> searchList = userRepository.searchUser(query);
        return searchList;
    }

}

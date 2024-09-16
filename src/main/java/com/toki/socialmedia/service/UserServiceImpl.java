package com.toki.socialmedia.service;
import com.toki.socialmedia.exception.UserException;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.UserRepository;
import com.toki.socialmedia.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        list = userRepository.findAll();
        return list;
    }

    @Override
    public User getUserById(UUID userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User with id: " + userId + " not found");
    }

    @Override
    public User followUser(UUID userId1, UUID userId2) throws UserException {
        User user2 = getUserById(userId2);
        User user1 = getUserById(userId1);
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
    public User updateUser(UUID userId, User user) throws UserException {
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
            if(user.getGender() != null){
                changeUser.setGender(user.getGender());
            }
            User afterUpdate = userRepository.save(changeUser);
            return afterUpdate;
        }
        throw new UserException("User with id: " + userId + " not found");
    }

    @Override
    public List<User> searchUser(String query) {
        List<User> searchList = userRepository.searchUser(query);
        return searchList;
    }

    @Override
    public User getUserByToken(String token) {
        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);
        return user;
    }

}

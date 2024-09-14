package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public List<User> getAllUser(){
        List<User> list = userServiceImpl.findAllUser();
        return list;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) throws Exception {
        User user = userServiceImpl.findUserById(userId);
        return user;
    }

    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable UUID userId, @RequestBody User user) throws Exception {
        User updateUser = userServiceImpl.updateUser(userId, user);
        return updateUser;
    }

    @PutMapping("/{userId1}/follow/{userId2}")
    public User followUser (@PathVariable UUID userId1, @PathVariable UUID userId2) throws Exception {
        User user1 = userServiceImpl.followUser(userId1, userId2);
        return user1;
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam String query){
        List<User> searchList = userServiceImpl.searchUser(query);
        return searchList;
    }

//    @DeleteMapping("/users/delete/{userId}")
//    public String deleteUser(@PathVariable UUID userId) throws Exception {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            userRepository.delete(user.get());
//            return "User with id: " + userId + " has been deleted";
//        }
//        throw new Exception("User with id: " + userId + " not found");
//    }
}

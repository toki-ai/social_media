package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.UserRepository;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public List<User> getAllUser(){
        List<User> list = userServiceImpl.findAllUser();
        return list;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Integer userId) throws Exception {
        User user = userServiceImpl.findUserById(userId);
        return user;
    }

    @PostMapping("/users/create")
    public User createUser(@RequestBody User user){
        User saveUser = userServiceImpl.registerUser(user);
        return saveUser;
    }

    @PutMapping("/users/update/{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody User user) throws Exception {
        User updateUser = userServiceImpl.updateUser(userId, user);
        return updateUser;
    }

    @PutMapping("/users/{userId1}/follow/{userId2}")
    public User followUser (@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user1 = userServiceImpl.followUser(userId1, userId2);
        return user1;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam String query){
        List<User> searchList = userServiceImpl.searchUser(query);
        return searchList;
    }

//    @DeleteMapping("/users/delete/{userId}")
//    public String deleteUser(@PathVariable Integer userId) throws Exception {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            userRepository.delete(user.get());
//            return "User with id: " + userId + " has been deleted";
//        }
//        throw new Exception("User with id: " + userId + " not found");
//    }

}

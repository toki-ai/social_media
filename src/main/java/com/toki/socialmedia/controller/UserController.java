package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
    public List<User> getAllUser(){
        List<User> list = userServiceImpl.getAllUser();
        return list;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) throws Exception {
        User user = userServiceImpl.getUserById(userId);
        return user;
    }

    @GetMapping("/profile")
    public User getUserByToken(@RequestHeader("Authorization") String token) {
        User reqUser = userServiceImpl.getUserByToken(token);
        reqUser.setPassword(null);
        return reqUser;
    }

    @PutMapping("/update")
    public User updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) throws Exception {
        User reqUser = userServiceImpl.getUserByToken(token);
        User updateUser = userServiceImpl.updateUser(reqUser.getId(), user);
        return updateUser;
    }

    @PutMapping("/follow/{userId2}")
    public User followUser (@RequestHeader("Authorization") String token, @PathVariable UUID userId2) throws Exception {
        User reqUser = userServiceImpl.getUserByToken(token);
        User user1 = userServiceImpl.followUser(reqUser.getId(), userId2);
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

package com.toki.socialmedia.controller;

import com.toki.socialmedia.exception.UserException;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Operation(summary = "Get all users")
    @GetMapping("")
    public List<User> getAllUser(){
        List<User> list = userServiceImpl.getAllUser();
        return list;
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) throws UserException {
        User user = userServiceImpl.getUserById(userId);
        return user;
    }

    @Operation(summary = "Get user profile by token")
    @GetMapping("/profile")
    public User getUserByToken(@RequestHeader("Authorization") String token) {
        User reqUser = userServiceImpl.getUserByToken(token);
        reqUser.setPassword(null);
        return reqUser;
    }

    @Operation(summary = "Update user")
    @PutMapping("/update")
    public User updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) throws UserException {
        User reqUser = userServiceImpl.getUserByToken(token);
        User updateUser = userServiceImpl.updateUser(reqUser.getId(), user);
        return updateUser;
    }

    @Operation(summary = "Follow user")
    @PutMapping("/follow/{userId2}")
    public User followUser (@RequestHeader("Authorization") String token, @PathVariable UUID userId2) throws UserException {
        User reqUser = userServiceImpl.getUserByToken(token);
        User user1 = userServiceImpl.followUser(reqUser.getId(), userId2);
        return user1;
    }

    @Operation(summary = "Search user")
    @GetMapping("/search")
    public List<User> searchUser(@RequestParam String query){
        List<User> searchList = userServiceImpl.searchUser(query);
        return searchList;
    }

//    @DeleteMapping("/users/delete/{userId}")
//    public String deleteUser(@PathVariable UUID userId) throws UserException {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            userRepository.delete(user.get());
//            return "User with id: " + userId + " has been deleted";
//        }
//        throw new UserException("User with id: " + userId + " not found");
//    }
}

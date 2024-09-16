package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Story;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.StoryServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("stories")
public class StoryController {
    private final StoryServiceImpl storyServiceImpl;
    private final UserServiceImpl userServiceImpl;

    public StoryController(StoryServiceImpl storyServiceImpl, UserServiceImpl userServiceImpl) {
        this.storyServiceImpl = storyServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    public List<Story> getAllStories(){
        List<Story> listStory = storyServiceImpl.getAllStories();
        return listStory;
    }

    @GetMapping("/userStories/{userId}")
    public List<Story> getStoriesByUserId(@PathVariable("userId") UUID userId) throws Exception {
        List<Story> listStory = storyServiceImpl.getStoriesByUserId(userId);
        return listStory;
    }

    @GetMapping("/{storyId}")
    public Story getStoryById(@PathVariable("storyId") UUID storyId) throws Exception {
        Story story = storyServiceImpl.getStoryById(storyId);
        return story;
    }

    @PostMapping("/create")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String token) throws Exception {
        User user = userServiceImpl.getUserByToken(token);
        Story newStory = storyServiceImpl.createStory(user.getId(), story);
        return newStory;
    }
}

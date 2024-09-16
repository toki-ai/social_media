package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Story;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.StoryRepository;
import com.toki.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Story> getAllStories() {
        List<Story> listStory = new ArrayList<>();
        listStory = storyRepository.findAll();
        return listStory;
    }

    @Override
    public Story getStoryById(UUID storyID) throws Exception {
        Optional<Story> story = storyRepository.findById(storyID);
        if(story.isPresent()){
            return story.get();
        }
        throw new Exception("Story not found");
    }

    @Override
    public Story createStory(UUID userId, Story story) throws Exception {
        Story newStory = new Story();
        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setTimestamp(LocalDateTime.now());
        User user = userServiceImpl.findUserById(userId);
        newStory.setUser(user);
        Story savedStory = storyRepository.save(newStory);
        return savedStory;
    }

    @Override
    public List<Story> getStoriesByUserId(UUID userId) throws Exception {
        List<Story> listStory = new ArrayList<>();
        User user = userServiceImpl.findUserById(userId);
        listStory = storyRepository.findByUser(user);
        return listStory;
    }
}

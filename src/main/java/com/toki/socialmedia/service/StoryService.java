package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Story;

import java.util.List;
import java.util.UUID;

public interface StoryService {
    List<Story> getAllStories();
    Story getStoryById(UUID storyID) throws Exception;
    Story createStory(UUID userId, Story story) throws Exception;
    List<Story> getStoriesByUserId(UUID userId) throws Exception;

}

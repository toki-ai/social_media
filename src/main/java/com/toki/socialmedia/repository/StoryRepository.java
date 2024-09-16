package com.toki.socialmedia.repository;

import com.toki.socialmedia.model.Story;
import com.toki.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoryRepository extends JpaRepository<Story, UUID> {
    List<Story> findByUser(User user);
}

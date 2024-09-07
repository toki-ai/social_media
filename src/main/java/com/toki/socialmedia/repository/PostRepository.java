package com.toki.socialmedia.repository;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findByUser(User user);
}

package com.toki.socialmedia.repository;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    public List<Post> findByUser(User user);

    Optional<Post> findById(UUID postId);

    void deleteById(UUID postId);
}

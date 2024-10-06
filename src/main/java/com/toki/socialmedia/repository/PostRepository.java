package com.toki.socialmedia.repository;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    public List<Post> findByUser(User user);

    Optional<Post> findById(UUID postId);

    void deleteById(UUID postId);

    @Query("SELECT p FROM Post p WHERE p.caption LIKE %:query%")
    List<Post> searchPost (@Param("query") String query);
}

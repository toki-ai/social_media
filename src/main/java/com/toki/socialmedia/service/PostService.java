package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;

import java.nio.channels.Pipe;
import java.util.List;
import java.util.UUID;

public interface PostService {

    public List<Post> findAllPost();

    public List<Post> findPostByUser(User user);

    public Post findPostById(UUID postId) throws Exception;

    public Post createNewPost(UUID userId, Post post);

    public String deletePost(UUID postId, UUID userId) throws Exception;

    public Post updatePost(UUID postId, Post post);

    public Post likePost(UUID postId, UUID userId) throws Exception;

    public Post savePost(UUID postId, UUID userId) throws Exception;

}

package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;

import java.util.List;
import java.util.UUID;

public interface PostService {

    public List<Post> getAllPost();

    public List<Post> getPostByUser(User user);

    public Post getPostById(UUID postId) throws Exception;

    public Post createNewPost(UUID userId, Post post);

    public String deletePost(UUID postId, UUID userId) throws Exception;

    public Post updatePost(UUID postId, Post post);

    public Post likePost(UUID postId, UUID userId) throws Exception;

    public Post savePost(UUID postId, UUID userId) throws Exception;

}

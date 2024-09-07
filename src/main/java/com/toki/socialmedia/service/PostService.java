package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;

import java.nio.channels.Pipe;
import java.util.List;

public interface PostService {

    public List<Post> findPostByUser(User user);

    public Post findPostById(Integer postId) throws Exception;

    public Post createNewPost(Post post);

    public String deletePost(Integer postId, Integer userId) throws Exception;

    public Post updatePost(Integer postId, Post post);

    public Post likePost(Integer postId, Integer userId) throws Exception;

    public Post savePost(Integer postId, Integer userId) throws Exception;



}

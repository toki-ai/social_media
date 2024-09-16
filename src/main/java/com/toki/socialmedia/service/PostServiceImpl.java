package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.PostRepository;
import com.toki.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Post> getAllPost() {
        List<Post> list = new ArrayList<>();
        list = postRepository.findAll();
        return list;
    }

    @Override
    public List<Post> getPostByUser(User user) {
        List<Post> list = postRepository.findByUser(user);
        return list;
    }

    @Override
    public Post getPostById(UUID postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return post.get();
        }
        throw new Exception("Post with id " + postId + " not found");
    }

    @Override
    public Post createNewPost(UUID userId, Post post) {
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        User user = null;
        try {
            user = userServiceImpl.getUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        newPost.setUser(user);
        newPost.setDate(LocalDateTime.now());
        Post updatePost = postRepository.save(newPost);
        return updatePost;
    }

    @Override
    public String deletePost(UUID postId, UUID userId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        Optional<User> user = userRepository.findById(userId);
        if (post.isPresent() && user.isPresent()) {
            if (post.get().getUser().getId().equals(userId)) {
                postRepository.deleteById(postId);
                return "Post with id " + postId + " deleted successfully";
            }
            throw new Exception("You are not authorized to delete this post");
        }
        throw new Exception("You are can not delete this post");
    }

    @Override
    public Post updatePost(UUID postId, Post post) {
        return null;
    }

    @Override
    public Post likePost(UUID postId, UUID userId) throws Exception {
        User user = userRepository.findById(userId).get();
        Post post = getPostById(postId);
        List<User> liked = post.getLiked();
        if(liked == null){
            liked = new ArrayList<>();
        }
        if (liked.contains(user)) {
            liked.remove(user);
            post.setLiked(liked);
        } else {
            liked.add(user);
            post.setLiked(liked);
        }
        Post updatePost = postRepository.save(post);
        return updatePost;
    }

    @Override
    public Post savePost(UUID postId, UUID userId) throws Exception {
        User user = userRepository.findById(userId).get();
        Post post = getPostById(postId);
        List<Post> save = user.getSaved();
        if(save == null){
            save = new ArrayList<>();
        }
        if (save.contains(post)) {
            save.remove(post);
            user.setSaved(save);
        } else {
            save.add(post);
            user.setSaved(save);
        }
        userRepository.save(user);
        return post;
    }
}

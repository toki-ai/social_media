package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.PostRepository;
import com.toki.socialmedia.response.ApiResponse;
import com.toki.socialmedia.service.PostServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostServiceImpl postServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> post = postServiceImpl.findAllPost();
        return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable UUID postId) {
        try {
            Post post = postServiceImpl.findPostById(postId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getAllPostByUserId(@PathVariable UUID userId) {
        try {
            User user = userServiceImpl.findUserById(userId);
            List<Post> post = postServiceImpl.findPostByUser(user);
            return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Post> createNewPost(@PathVariable UUID userId, @RequestBody Post post) {
        Post newPost = postServiceImpl.createNewPost(userId, post);
        return new ResponseEntity<Post>(newPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}/delete/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable UUID postId, @PathVariable UUID userId){
        try {
            String message = postServiceImpl.deletePost(postId, userId);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, message), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{postId}/save/{userId}")
    public ResponseEntity<Post> savePost(@PathVariable UUID postId, @PathVariable UUID userId) {
        try {
            Post post = postServiceImpl.savePost(postId, userId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{postId}/like/{userId}")
    public ResponseEntity<Post> likePost(@PathVariable UUID postId, @PathVariable UUID userId) {
        try {
            Post post = postServiceImpl.likePost(postId, userId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

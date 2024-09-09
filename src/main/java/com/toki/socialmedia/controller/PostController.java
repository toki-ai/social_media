package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.PostRepository;
import com.toki.socialmedia.response.ApiResponse;
import com.toki.socialmedia.service.PostServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostServiceImpl postServiceImpl;
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> post = postServiceImpl.findAllPost();
        return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer postId) {
        try {
            Post post = postServiceImpl.findPostById(postId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> getAllPostByUserId(@PathVariable Integer userId) {
        try {
            User user = userServiceImpl.findUserById(userId);
            List<Post> post = postServiceImpl.findPostByUser(user);
            return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/posts/create/{userId}")
    public ResponseEntity<Post> createNewPost(@PathVariable Integer userId, @RequestBody Post post) {
        Post newPost = postServiceImpl.createNewPost(userId, post);
        return new ResponseEntity<Post>(newPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{userId}/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId){
        try {
            String message = postServiceImpl.deletePost(postId, userId);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, message), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/posts/{userId}/save/{postId}")
    public ResponseEntity<Post> savePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        try {
            Post post = postServiceImpl.savePost(postId, userId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/posts/{userId}/like/{postId}")
    public ResponseEntity<Post> likePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        try {
            Post post = postServiceImpl.likePost(postId, userId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

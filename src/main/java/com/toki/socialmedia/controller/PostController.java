package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.response.ApiResponse;
import com.toki.socialmedia.service.PostServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Operation(summary = "Get all post")
    @GetMapping("")
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> post = postServiceImpl.getAllPost();
        return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
    }

    @Operation(summary = "Get post by id")
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable UUID postId) {
        try {
            Post post = postServiceImpl.getPostById(postId);
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get all post by user")
    @GetMapping("/userPosts")
    public ResponseEntity<List<Post>> getAllPostByUserId(@RequestHeader("Authorization") String token) {
        User reqUser = userServiceImpl.getUserByToken(token);
        try {
            List<Post> post = postServiceImpl.getPostByUser(reqUser);
            return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get all post by user id")
    @GetMapping("/userPosts/{userId}")
    public ResponseEntity<List<Post>> getAllPostByUserId(@PathVariable UUID userId) {
        try {
            User reqUser = userServiceImpl.getUserById(userId);
            List<Post> post = postServiceImpl.getPostByUser(reqUser);
            return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Create post")
    @PostMapping("/create")
    public ResponseEntity<Post> createNewPost(@RequestHeader("Authorization") String token, @RequestBody Post post) {
        User reqUser = userServiceImpl.getUserByToken(token);
        Post newPost = postServiceImpl.createNewPost(reqUser.getId(), post);
        return new ResponseEntity<Post>(newPost, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Delete post")
    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String token, @PathVariable UUID userId){
        try {
            User reqUser = userServiceImpl.getUserByToken(token);
            String message = postServiceImpl.deletePost(reqUser.getId(), userId);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, message), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Save post")
    @PutMapping("/{postId}/save")
    public ResponseEntity<Post> savePost(@PathVariable UUID postId,@RequestHeader("Authorization") String token) {
        try {
            User reqUser = userServiceImpl.getUserByToken(token);
            Post post = postServiceImpl.savePost(postId, reqUser.getId());
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Like post")
    @PutMapping("/{postId}/like")
    public ResponseEntity<Post> likePost(@PathVariable UUID postId, @RequestHeader("Authorization") String token) {
        try {
            User reqUser = userServiceImpl.getUserByToken(token);
            Post post = postServiceImpl.likePost(postId, reqUser.getId());
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Search post")
    @GetMapping("/search")
    public ResponseEntity<List<Post>> likePost(@RequestParam String query) {
        try {
            List<Post> posts = postServiceImpl.searchPost(query);
            return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

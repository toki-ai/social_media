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

    @GetMapping("")
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

    @GetMapping("/userPosts")
    public ResponseEntity<List<Post>> getAllPostByUserId(@RequestHeader("Authorization") String token) {
        User reqUser = userServiceImpl.findUserByToken(token);
        try {
            List<Post> post = postServiceImpl.findPostByUser(reqUser);
            return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createNewPost(@RequestHeader("Authorization") String token, @RequestBody Post post) {
        User reqUser = userServiceImpl.findUserByToken(token);
        Post newPost = postServiceImpl.createNewPost(reqUser.getId(), post);
        return new ResponseEntity<Post>(newPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String token, @PathVariable UUID userId){
        try {
            User reqUser = userServiceImpl.findUserByToken(token);
            String message = postServiceImpl.deletePost(reqUser.getId(), userId);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, message), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{postId}/save")
    public ResponseEntity<Post> savePost(@PathVariable UUID postId,@RequestHeader("Authorization") String token) {
        try {
            User reqUser = userServiceImpl.findUserByToken(token);
            Post post = postServiceImpl.savePost(postId, reqUser.getId());
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{postId}/like")
    public ResponseEntity<Post> likePost(@PathVariable UUID postId, @RequestHeader("Authorization") String token) {
        try {
            User reqUser = userServiceImpl.findUserByToken(token);
            Post post = postServiceImpl.likePost(postId, reqUser.getId());
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

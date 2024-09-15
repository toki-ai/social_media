package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Comment;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.CommentServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/create/{postId}")
    public Comment getComments(@RequestBody Comment comment, @RequestHeader("Authorization") String token, @PathVariable UUID postId) throws Exception {
        User user = userServiceImpl.findUserByToken(token);
        Comment newComment = commentServiceImpl.createComment(postId, user.getId(), comment);
        return newComment;
    }

    @PutMapping("/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String token, @PathVariable UUID commentId) throws Exception {
        User user = userServiceImpl.findUserByToken(token);
        Comment comment = commentServiceImpl.likeComment(user.getId(), commentId);
        return comment;
    }
}

package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Comment;
import com.toki.socialmedia.model.Post;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.CommentRepository;
import com.toki.socialmedia.repository.PostRepository;
import com.toki.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public Comment createComment(UUID postId, UUID userId, Comment comment) throws Exception {
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setCreatedAt(LocalDateTime.now());
        User user = userServiceImpl.findUserById(userId);
        newComment.setUser(user);
        Post post = postRepository.findById(postId).orElseThrow(() -> new Exception("Post not found"));
        if(post.getComments() == null) {
            post.setComments(new ArrayList<>());
        }
            List <Comment> commentList = post.getComments();
            commentList.add(newComment);
            post.setComments(commentList);
            Comment saveComment = commentRepository.save(newComment);
            return saveComment;
    }

    @Override
    public Comment getCommentById(UUID commentId) throws Exception {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            return comment.get();
        }
        throw new Exception("Comment not found");
    }

    @Override
    public void deleteComment(UUID postId, UUID commentId) {
        commentRepository.deleteById(commentId);
        Post post = postRepository.findById(postId).get();
        List<Comment> commentList = post.getComments();
        commentList.removeIf(comment -> comment.getId().equals(commentId));
        post.setComments(commentList);
        postRepository.save(post);
    }

//    @Override
//    public Comment updateComment(UUID commentId, Comment comment) {
//        Comment updateComment = commentRepository.findById(commentId).get();
//        if(comment.getContent() != null) {
//            updateComment.setContent(comment.getContent());
//        }
//        if(comment.getUser() != null) {
//            updateComment.setUser(comment.getUser());
//        }
//
//        Comment saveComment = commentRepository.save(updateComment);
//
//        return saveComment;
//    }

    @Override
    public Comment likeComment(UUID userId, UUID commentId) throws Exception {
        Comment comment = getCommentById(commentId);
        if(comment.getLiked() == null) {
            comment.setLiked(new ArrayList<>());
        }
        User user = userServiceImpl.findUserById(userId);
        if(comment.getLiked().contains(user)) {
            comment.getLiked().remove(user);
        }else{
            comment.getLiked().add(user);
        }
        Comment saveComment = commentRepository.save(comment);
        return saveComment;
    }
}

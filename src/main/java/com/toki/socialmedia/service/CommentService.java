package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    Comment createComment(UUID postId, UUID userId, Comment comment) throws Exception;
    Comment getCommentById(UUID commentId) throws Exception;
    void deleteComment(UUID postId, UUID commentId);
    Comment likeComment(UUID userId, UUID commentId) throws Exception;
}

package com.py.software.service;

import com.py.software.entity.Comment;

import java.util.List;

public interface CommentInterface {
    Comment findCommentById(Long comment_id);
    void addComment(Comment comment);
    void deleteComment(Long comment_id);
    List<Comment> queryComment(String name);
    List<Comment> findCommentByUser(Long user_id);
    List<Comment> findCommentByMovie(Long movie_id);
}

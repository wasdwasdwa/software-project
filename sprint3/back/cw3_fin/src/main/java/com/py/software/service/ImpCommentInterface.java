package com.py.software.service;

import com.py.software.entity.Comment;
import com.py.software.repository.CommentRepository;
import com.py.software.repository.MovieRepository;
import com.py.software.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ImpCommentInterface implements CommentInterface{

    @Autowired
    private CommentRepository commentRepository;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Comment findCommentById(Long comment_id) {
        return commentRepository.findById(comment_id).get();
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public void deleteComment(Long comment_id) {
        commentRepository.deleteById(comment_id);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Comment> queryComment(String name) {
        return commentRepository.findCommentByContent(name);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Comment> findCommentByUser(Long user_id) {
        return commentRepository.findCommentByUserId(user_id);
    }

    @Transactional(propagation=Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Comment> findCommentByMovie(Long movie_id) {
        return commentRepository.findCommentByMovieId(movie_id);
    }
}

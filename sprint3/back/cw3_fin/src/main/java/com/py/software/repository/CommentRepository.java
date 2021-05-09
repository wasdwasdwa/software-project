package com.py.software.repository;

import com.py.software.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "select * from comment where comment_content like concat('%',?1,'%') ",nativeQuery = true)
    List<Comment> findCommentByContent(String content);
    @Query(value = "select * from comment where user_id = ?1",nativeQuery = true)
    List<Comment> findCommentByUserId(Long user_id);
    @Query(value = "select * from comment where movie_id = ?1",nativeQuery = true)
    List<Comment> findCommentByMovieId(Long movie_id);
}

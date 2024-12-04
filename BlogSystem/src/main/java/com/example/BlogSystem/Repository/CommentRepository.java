package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Integer id);

    @Query("select c from Comment c where c.userId = ?1")
    List<Comment> getCommentByUserId(Integer userId);

    @Query("select c from Comment c where c.postId = ?1")
    List<Comment> getCommentByPostId(Integer postId);

    @Query("select c from Comment c where c.postId = ?1 and c.userId = ?2")
    List<Comment> getCommentByPostIdAndUserId(Integer postId, Integer userId);

    List<Comment> findCommentByCommentDateBefore(LocalDateTime date);

    List<Comment> findCommentByCommentDateAfter(LocalDateTime date);

    List<Comment> findCommentByCommentDateBetween(LocalDateTime firstDate, LocalDateTime lastDate);
}

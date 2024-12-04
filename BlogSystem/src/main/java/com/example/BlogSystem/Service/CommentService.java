package com.example.BlogSystem.Service;

import com.example.BlogSystem.ApiResponse.ApiException;
import com.example.BlogSystem.Model.Comment;
import com.example.BlogSystem.Repository.CommentRepository;
import com.example.BlogSystem.Repository.PostRepository;
import com.example.BlogSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        if (userRepository.findUserById(comment.getUserId()) == null)
            throw new ApiException("User with ID: " + comment.getUserId() + " was not found");

        if (postRepository.findPostById(comment.getPostId()) == null)
            throw new ApiException("Post with ID: " + comment.getPostId() + " was not found");

        commentRepository.save(comment);
    }

    public void updateComment(Integer id, Comment comment) {
        Comment oldComment = commentRepository.findCommentById(id);
        if (oldComment == null)
            throw new ApiException("Comment with ID: " + id + " was not found");

        if (userRepository.findUserById(comment.getUserId()) == null)
            throw new ApiException("User with ID: " + comment.getUserId() + " was not found");

        if (postRepository.findPostById(comment.getPostId()) == null)
            throw new ApiException("Post with ID: " + comment.getPostId() + " was not found");

        oldComment.setUserId(comment.getUserId());
        oldComment.setPostId(comment.getPostId());
        oldComment.setContent(comment.getContent());
        commentRepository.save(oldComment);
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findCommentById(id);
        if (comment == null)
            throw new ApiException("Comment with ID: " + id + " was not found");

        commentRepository.delete(comment);
    }

    public Comment getCommentById(Integer id) {
        Comment comment = commentRepository.findCommentById(id);
        if (comment == null)
            throw new ApiException("Comment with ID: " + id + " was not found");

        return comment;
    }

    public List<Comment> getCommentByUser(Integer id) {
        List<Comment> userComments = commentRepository.getCommentByUserId(id);
        if (userComments.isEmpty())
            throw new ApiException("There are no comments by this user");

        return userComments;
    }

    public List<Comment> getCommentByPost(Integer id) {
        List<Comment> postComments = commentRepository.getCommentByPostId(id);
        if (postComments.isEmpty())
            throw new ApiException("There are no comments on this post");

        return postComments;
    }

    public List<Comment> getCommentByPostAndUser(Integer postId, Integer userId) {
        List<Comment> postUserComments = commentRepository.getCommentByPostIdAndUserId(postId, userId);
        if (postUserComments.isEmpty())
            throw new ApiException("There are no comments on this post by this user");

        return postUserComments;
    }

    public List<Comment> getCommentBeforeDate(LocalDateTime date) {
        List<Comment> comments = commentRepository.findCommentByCommentDateBefore(date);
        if (comments.isEmpty())
            throw new ApiException("There are no comments before: " + date);

        return comments;
    }

    public List<Comment> getCommentAfterDate(LocalDateTime date) {
        List<Comment> comments = commentRepository.findCommentByCommentDateAfter(date);
        if (comments.isEmpty())
            throw new ApiException("There are no comments after: " + date);

        return comments;
    }

    public List<Comment> getCommentBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate) {
        List<Comment> comments = commentRepository.findCommentByCommentDateBetween(firstDate, lastDate);
        if (comments.isEmpty())
            throw new ApiException("There are no comments between " + firstDate + " and " + lastDate);

        return comments;
    }
}

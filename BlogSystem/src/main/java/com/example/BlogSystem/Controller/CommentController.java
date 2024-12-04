package com.example.BlogSystem.Controller;

import com.example.BlogSystem.ApiResponse.ApiResponse;
import com.example.BlogSystem.Model.Comment;
import com.example.BlogSystem.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get")
    public ResponseEntity getAllComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        commentService.updateComment(id, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment with ID: " + id + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment with ID: " + id + " has been deleted successfully"));
    }

    @GetMapping("/get/by-id/{id}")
    public ResponseEntity getCommentById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(commentService.getCommentById(id));
    }

    @GetMapping("/get/by-user/{id}")
    public ResponseEntity getCommentByUser(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(commentService.getCommentByUser(id));
    }

    @GetMapping("/get/by-post/{id}")
    public ResponseEntity getCommentByPost(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(commentService.getCommentByPost(id));
    }

    @GetMapping("/get/by-post/{postId}/by-user/{userId}")
    public ResponseEntity getCommentByPostAndUser(@PathVariable Integer postId, @PathVariable Integer userId) {
        return ResponseEntity.status(200).body(commentService.getCommentByPostAndUser(postId, userId));
    }

    @GetMapping("/get/before-date")
    public ResponseEntity getCommentBeforeDate(@RequestBody LocalDateTime date) {
        return ResponseEntity.status(200).body(commentService.getCommentBeforeDate(date));
    }

    @GetMapping("/get/after-date")
    public ResponseEntity getCommentAfterDate(@RequestBody LocalDateTime date) {
        return ResponseEntity.status(200).body(commentService.getCommentAfterDate(date));
    }

    @GetMapping("/get/between-date")
    public ResponseEntity getCommentBetweenDate(@RequestParam LocalDateTime firstDate, @RequestParam LocalDateTime lastDate) {
        return ResponseEntity.status(200).body(commentService.getCommentBetweenDate(firstDate, lastDate));
    }
}

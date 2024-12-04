package com.example.BlogSystem.Controller;

import com.example.BlogSystem.ApiResponse.ApiResponse;
import com.example.BlogSystem.Model.Post;
import com.example.BlogSystem.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/get")
    public ResponseEntity getAllPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        postService.updatePost(id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post with ID: " + id + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post with ID: " + id + " has been deleted successfully"));
    }

    @GetMapping("/get/by-id/{id}")
    public ResponseEntity getPostById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getPostById(id));
    }

    @GetMapping("/get/by-title/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title) {
        return ResponseEntity.status(200).body(postService.getPostByTitle(title));
    }

    @GetMapping("/get/by-category/{id}")
    public ResponseEntity getPostByCategory(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getPostByCategory(id));
    }

    @GetMapping("/get/by-user/{id}")
    public ResponseEntity getPostByUser(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(postService.getPostsByUser(id));
    }

    @GetMapping("/get/by-user/{userId}/category/{categoryId}")
    public ResponseEntity getPostByUserAndCategory(@PathVariable Integer userId, @PathVariable Integer categoryId) {
        return ResponseEntity.status(200).body(postService.getPostByUserAndCategory(userId, categoryId));
    }

    @GetMapping("/get/before-date")
    public ResponseEntity getPostBeforeDate(@RequestBody LocalDateTime date) {
        return ResponseEntity.status(200).body(postService.getPostBeforeDate(date));
    }

    @GetMapping("/get/after-date")
    public ResponseEntity getPostAfterDate(@RequestBody LocalDateTime date) {
        return ResponseEntity.status(200).body(postService.getPostAfterDate(date));
    }

    @GetMapping("/get/between-date")
    public ResponseEntity getPostBetweenDate(@RequestParam LocalDateTime firstDate, @RequestParam LocalDateTime lastDate) {
        return ResponseEntity.status(200).body(postService.getPostBetweenDate(firstDate, lastDate));
    }
}

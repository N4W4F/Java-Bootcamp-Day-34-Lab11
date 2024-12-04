package com.example.BlogSystem.Service;

import com.example.BlogSystem.ApiResponse.ApiException;
import com.example.BlogSystem.Model.Post;
import com.example.BlogSystem.Repository.CategoryRepository;
import com.example.BlogSystem.Repository.PostRepository;
import com.example.BlogSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
        if (categoryRepository.findCategoryById(post.getCategoryId()) == null)
            throw new ApiException("Category wit this ID was not found");

        if (userRepository.findUserById(post.getUserId()) == null)
            throw new ApiException("User with this ID was not found");

        postRepository.save(post);
    }

    public void updatePost(Integer id, Post post) {
        Post oldPost = postRepository.findPostById(id);
        if (oldPost == null)
            throw new ApiException("Post with ID: " + id + " was not found");

        if (categoryRepository.findCategoryById(post.getCategoryId()) == null)
            throw new ApiException("Category wit this ID was not found");

        if (userRepository.findUserById(post.getUserId()) == null)
            throw new ApiException("User with this ID was not found");

        oldPost.setTitle(post.getTitle());
        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setUserId(post.getUserId());
        oldPost.setContent(post.getContent());
        postRepository.save(oldPost);
    }

    public void deletePost(Integer id) {
        Post post = postRepository.findPostById(id);
        if (post == null)
            throw new ApiException("Post with ID: " + id + " was not found");

        postRepository.delete(post);
    }

    public Post getPostById(Integer id) {
        Post post = postRepository.findPostById(id);
        if (post == null)
            throw new ApiException("Post with ID: " + id + " was not found");

        return post;
    }

    public Post getPostByTitle(String title) {
        Post post = postRepository.findPostByTitle(title);
        if (post == null)
            throw new ApiException("Post with this title was not found");

        return post;
    }

    public List<Post> getPostByCategory(Integer categoryId) {
        if (categoryRepository.findCategoryById(categoryId) == null)
            throw new ApiException("Category with ID: " + categoryId + " was not found");

        List<Post> categorizedPosts = postRepository.findPostByCategoryId(categoryId);
        if (categorizedPosts.isEmpty())
            throw new ApiException("There are no posts in this category");

        return categorizedPosts;
    }

    public List<Post> getPostsByUser(Integer userId) {
        if (userRepository.findUserById(userId) == null)
            throw new ApiException("User with ID: " + userId + " was not found");

        List<Post> userPosts = postRepository.findPostByUserId(userId);
        if (userPosts.isEmpty())
            throw new ApiException("There are no posts by this user");

        return userPosts;
    }

    public List<Post> getPostByUserAndCategory(Integer userId, Integer categoryId) {
        if (userRepository.findUserById(userId) == null)
            throw new ApiException("User with ID: " + userId + " was not found");

        if (categoryRepository.findCategoryById(categoryId) == null)
            throw new ApiException("Category with ID: " + categoryId + " was not found");

        List<Post> userPosts = postRepository.getPostByUserIdAndCategoryId(userId, categoryId);
        if (userPosts.isEmpty())
            throw new ApiException("There are no posts by this user in this category");

        return userPosts;
    }

    public List<Post> getPostBeforeDate(LocalDateTime date) {
        List<Post> posts = postRepository.getPostBeforeDate(date);
        if (posts.isEmpty())
            throw new ApiException("There are no posts before: " + date);

        return posts;
    }

    public List<Post> getPostAfterDate(LocalDateTime date) {
        List<Post> posts = postRepository.getPostAfterDate(date);
        if (posts.isEmpty())
            throw new ApiException("There are no posts after: " + date);

        return posts;
    }

    public List<Post> getPostBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate) {
        List<Post> posts = postRepository.getPostBetweenDate(firstDate, lastDate);
        if (posts.isEmpty())
            throw new ApiException("There are no posts between " + firstDate + " and " + lastDate);

        return posts;
    }
}

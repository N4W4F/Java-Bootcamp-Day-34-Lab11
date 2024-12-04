package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);

    List<Post> findPostByCategoryId(Integer categoryId);

    List<Post> findPostByUserId(Integer userId);

    @Query("select p from Post p where p.userId = ?1 and p.categoryId = ?2")
    List<Post> getPostByUserIdAndCategoryId(Integer userId, Integer categoryId);

    Post findPostByTitle(String title);

    @Query("select p from Post p where p.publishDate < ?1")
    List<Post> getPostBeforeDate(LocalDateTime date);
    @Query("select p from Post p where p.publishDate > ?1")
    List<Post> getPostAfterDate(LocalDateTime date);

    @Query("select p from Post p where p.publishDate > ?1 and p.publishDate < ?2")
    List<Post> getPostBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate);
}

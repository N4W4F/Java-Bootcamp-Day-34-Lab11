package com.example.BlogSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "Category ID cannot be empty")
    private Integer categoryId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "User ID cannot be empty")
    private Integer userId;

    @Column(columnDefinition = "varchar(16) not null unique")
    @NotEmpty(message = "Post Title cannot be empty")
    private String title;

    @Column(columnDefinition = "varchar(50) not null")
    @NotEmpty(message = "Post Content cannot be empty")
    private String content;

    @Column(columnDefinition = "datetime default current_timestamp")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime publishDate;

    @PrePersist
    public void prePersist() {
        if (publishDate == null)
            publishDate = LocalDateTime.now();
    }
}

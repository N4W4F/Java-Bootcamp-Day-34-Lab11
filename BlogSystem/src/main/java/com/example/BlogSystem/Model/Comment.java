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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "User ID cannot be empty")
    private Integer userId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "Post ID cannot be empty")
    private Integer postId;

    @Column(columnDefinition = "varchar(50) not null")
    @NotEmpty(message = "Comment content cannot be empty")
    private String content;

    @Column(columnDefinition = "datetime default current_timestamp")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime commentDate;

    @PrePersist
    public void prePersist() {
        if (commentDate == null)
            commentDate = LocalDateTime.now();
    }
}

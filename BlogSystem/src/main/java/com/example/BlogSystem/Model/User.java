package com.example.BlogSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(16) not null unique")
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @Column(columnDefinition = "varchar(21) not null")
    @NotEmpty(message = "User Password cannot be empty")
    private String password;

    @Column(columnDefinition = "varchar(32) not null unique")
    @NotEmpty(message = "User Email cannot be empty")
    private String email;

    @Column(columnDefinition = "datetime not null default current_timestamp")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime registrationDate;

    @PrePersist
    public void prePersist() {
        if (registrationDate == null)
            registrationDate = LocalDateTime.now();
    }
}

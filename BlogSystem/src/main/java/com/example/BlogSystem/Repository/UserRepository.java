package com.example.BlogSystem.Repository;

import com.example.BlogSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByUsername(String username);

    @Query("select u from User u where u.email = ?1")
    User getUserByEmail(String email);

    @Query("select u from User u where u.registrationDate < ?1")
    List<User> getUserRegisteredBeforeDate(LocalDateTime date);

    @Query("select u from User u where u.registrationDate > ?1")
    List<User> getUserRegisteredAfterDate(LocalDateTime date);

    @Query("select u from User u where u.registrationDate > ?1 and u.registrationDate < ?2")
    List<User> getUserRegisteredBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate);
}

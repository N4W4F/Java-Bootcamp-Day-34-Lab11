package com.example.BlogSystem.Service;

import com.example.BlogSystem.ApiResponse.ApiException;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user) {
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null)
            throw new ApiException("User with ID: " + id + " was not found");

        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new ApiException("User with ID: " + id + " was not found");

        userRepository.delete(user);
    }

    public User getUserById(Integer id) {
        User user = userRepository.findUserById(id);
        if (user == null)
            throw new ApiException("User with ID: " + id + " was not found");

        return user;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new ApiException("User with this username was not found");

        return user;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null)
            throw  new ApiException("User with this email was not found");

        return user;
    }

    public List<User> getUserRegisteredBeforeDate(LocalDateTime date) {
        List<User> registeredUsers = userRepository.getUserRegisteredBeforeDate(date);
        if (registeredUsers.isEmpty())
            throw new ApiException("There are no registered users before: " + date);

        return registeredUsers;
    }

    public List<User> getUserRegisteredAfterDate(LocalDateTime date) {
        List<User> registeredUsers = userRepository.getUserRegisteredAfterDate(date);
        if (registeredUsers.isEmpty())
            throw new ApiException("There are no registered users after: " + date);

        return registeredUsers;
    }

    public List<User> getUserRegisteredBetweenDate(LocalDateTime firstDate, LocalDateTime lastDate) {
        List<User> registeredUsers = userRepository.getUserRegisteredBetweenDate(firstDate, lastDate);
        if (registeredUsers.isEmpty())
            throw new ApiException("There are no registered user between " + firstDate + " and " + lastDate);

        return registeredUsers;
    }
}

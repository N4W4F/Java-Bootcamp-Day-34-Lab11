package com.example.BlogSystem.Controller;

import com.example.BlogSystem.ApiResponse.ApiResponse;
import com.example.BlogSystem.Model.User;
import com.example.BlogSystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User has been added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        userService.updateUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has been updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User with ID: " + id + " has been deleted successfully"));
    }

    @GetMapping("/get/by-id/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }

    @GetMapping("/get/by-username/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(userService.getUserByUsername(username));
    }

    @GetMapping("/get/by-email")
    public ResponseEntity getUserByEmail(@RequestBody String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get/before-date")
    public ResponseEntity getUserRegisteredBeforeDate(@RequestBody LocalDateTime date) {
        return ResponseEntity.status(200).body(userService.getUserRegisteredBeforeDate(date));
    }

    @GetMapping("/get/after-date")
    public ResponseEntity getUserRegisteredAfterDate(@RequestBody LocalDateTime date) {
        return ResponseEntity.status(200).body(userService.getUserRegisteredAfterDate(date));
    }

    @GetMapping("/get/between-date")
    public ResponseEntity getUserRegisteredBetweenDate(@RequestParam LocalDateTime firstDate, @RequestParam LocalDateTime lastDate) {
        return ResponseEntity.status(200).body(userService.getUserRegisteredBetweenDate(firstDate, lastDate));
    }
}

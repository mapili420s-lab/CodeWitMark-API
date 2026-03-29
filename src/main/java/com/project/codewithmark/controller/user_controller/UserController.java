package com.project.codewithmark.controller.user_controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.codewithmark.dto.user_dto.LoginResponse;
import com.project.codewithmark.dto.user_dto.UserRequest;
import com.project.codewithmark.dto.user_dto.UserResponse;
import com.project.codewithmark.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        // Logic to retrieve all users from the database
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam String keyword) {
        // Logic to search users based on query parameters
        return ResponseEntity.ok(userService.searchUsers(keyword));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody UserRequest userRequest) {
        // Logic to register the user
        return ResponseEntity.status(201).body(userService.registerUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody UserRequest userRequest) {
        LoginResponse response = new LoginResponse();

        try {
            UserResponse userResponse = userService.authenticateUser(userRequest.getEmail(), userRequest.getPassword());
            response.setUsername(userResponse.getUsername());
            response.setEmail(userResponse.getEmail());
            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {
            response.setError(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
            @Valid @RequestBody UserRequest userRequest) {
        // Logic to update the user with the given ID
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> partiallyUpdateUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest) {
        // Logic to partially update the user with the given ID
        return ResponseEntity.ok(userService.partiallyUpdateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Logic to delete the user with the given ID
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

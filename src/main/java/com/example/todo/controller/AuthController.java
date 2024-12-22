package com.example.todo.controller;

import com.example.todo.dto.UserDto;
import com.example.todo.model.User;
import com.example.todo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Register user
    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User savedUser = authService.registerUser(user);
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), null);
    }

    // Login user
    @PostMapping("/login")
    public UserDto loginUser(@RequestParam String email, @RequestParam String password) {
        User loggedInUser = authService.loginUser(email, password);
        return new UserDto(loggedInUser.getId(), loggedInUser.getName(), loggedInUser.getEmail(), null);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        User user = authService.getUserById(id);
        return new UserDto(user.getId(), user.getName(), user.getEmail(), null);
    }
}

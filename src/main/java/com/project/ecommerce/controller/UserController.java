package com.project.ecommerce.controller;

import com.project.ecommerce.service.AuthService;
import com.project.ecommerce.model.User;
import com.project.ecommerce.service.UserService;
import com.project.ecommerce.dto.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        try {
            User newUser = userService.register(user);

            if (newUser == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("User already exists");
            }

            return ResponseEntity.ok(newUser);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // ✅ LOGIN (🔥 FIXED)
    // ✅ LOGIN (🔥 CLEAN & FIXED)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        try {
            User existingUser = userService.login(user.getEmail(), user.getPassword());

            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid credentials");
            }

            String token = authService.generateToken(existingUser);

            return ResponseEntity.ok(Map.of(
                    "id", existingUser.getId(),
                    "name", existingUser.getName(),
                    "email", existingUser.getEmail(),
                    "role", existingUser.getRole(),
                    "token", token
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login failed");
        }
    }

    // ✅ GET USER PROFILE
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable String id) {

        User user = userService.getUserProfile(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    // ✅ UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {

        User updated = userService.updateUser(id, user);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }
}
package com.project.ecommerce.service;

import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.config.JwtUtil;
import com.project.ecommerce.dto.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ REGISTER USER (WITH HASHED PASSWORD)
    public User registerUser(User user) {

        user.setEmail(user.getEmail().trim().toLowerCase());

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        user.setRole("USER");

        return userRepository.save(user);
    }

    // ✅ LOGIN USER (RETURN JWT TOKEN)
    public String loginUser(LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        User existingUser = userRepository
                .findByEmail(email)
                .orElse(null);

        if (existingUser == null) {
            throw new RuntimeException("Invalid Email or Password");
        }

        boolean isMatch = encoder.matches(
                request.getPassword(),
                existingUser.getPassword()
        );

        if (isMatch) {
            return jwtUtil.generateToken(existingUser); // ✅ consistent
        }

        throw new RuntimeException("Invalid Email or Password");
    }

    // ✅ FIXED METHOD (🔥 IMPORTANT)
    public String generateToken(User user) {
        return jwtUtil.generateToken(user); // ✅ return String + consistent
    }
}

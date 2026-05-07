package com.project.ecommerce.service;

import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    // ================================
    // ✅ REGISTER USER
    // ================================
    public User register(User user) {

        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            System.out.println("❌ Invalid user data");
            return null;
        }

        String email = user.getEmail().trim().toLowerCase();
        user.setEmail(email);

        user.setRole("USER");

        System.out.println("🟢 Registering user: " + email);

        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            System.out.println("❌ User already exists!");
            return null;
        }

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);

        System.out.println("✅ User registered successfully: " + savedUser.getEmail());

        return savedUser;
    }

    // ================================
    // ✅ LOGIN USER
    // ================================
    public User login(String email, String password) {

        if (email == null || password == null) {
            System.out.println("❌ Email or password is null");
            return null;
        }

        email = email.trim().toLowerCase();

        System.out.println("🔍 Attempting login for: " + email);

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            System.out.println("❌ User not found");
            return null;
        }

        User user = optionalUser.get();

        System.out.println("📧 Stored Email: " + user.getEmail());

        // 🔥 DEBUG LOGS (ADDED)
        System.out.println("🔑 Entered Password: " + password);
        System.out.println("🗄️ Stored Hash: " + user.getPassword());

        boolean isMatch = encoder.matches(password, user.getPassword());

        System.out.println("🔐 Password Match: " + isMatch);

        if (!isMatch) {
            System.out.println("❌ Invalid password");
            return null;
        }

        System.out.println("✅ Login successful");

        return user;
    }
    // ================================
    // ✅ GET PROFILE
    // ================================
    public User getUserProfile(String id) {
        return userRepository.findById(id).orElse(null);
    }

    // ================================
    // ✅ UPDATE USER
    // ================================
    public User updateUser(String id, User updatedUser) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null && updatedUser != null) {

            if (updatedUser.getName() != null)
                user.setName(updatedUser.getName());

            if (updatedUser.getPhone() != null)
                user.setPhone(updatedUser.getPhone());

            if (updatedUser.getAddress() != null)
                user.setAddress(updatedUser.getAddress());

            return userRepository.save(user);
        }

        return null;
    }

    // ================================
    // ✅ GET USER BY EMAIL
    // ================================
    public User getUserByEmail(String email) {

        if (email == null) return null;

        email = email.trim().toLowerCase();

        return userRepository.findByEmail(email).orElse(null);
    }
}
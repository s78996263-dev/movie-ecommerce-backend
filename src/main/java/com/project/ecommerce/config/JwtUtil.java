package com.project.ecommerce.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import com.project.ecommerce.model.User;

@Component
public class JwtUtil {

    // ✅ FIXED STATIC SECRET (DO NOT CHANGE AFTER DEPLOYMENT)
    private final String SECRET = "mysecretkeymysecretkeymysecretkey123"; // 32+ chars

    // ✅ GENERATE KEY FROM SECRET
    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ================================
    // ✅ GENERATE TOKEN
    // ================================
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole()) // ✅ include role
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getKey()) // ✅ FIXED
                .compact();
    }

    // ================================
    // ✅ EXTRACT EMAIL
    // ================================
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey()) // ✅ FIXED
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ================================
    // ✅ VALIDATE TOKEN
    // ================================
    public boolean validateToken(String token, String email) {
        try {
            final String extractedEmail = extractEmail(token);
            return (extractedEmail.equals(email) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    // ================================
    // ✅ CHECK EXPIRY
    // ================================
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey()) // ✅ FIXED
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    // ================================
    // ✅ EXTRACT ROLE
    // ================================
    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey()) // ✅ FIXED
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}
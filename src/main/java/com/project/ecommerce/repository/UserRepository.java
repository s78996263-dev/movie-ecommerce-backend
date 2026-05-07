package com.project.ecommerce.repository;

import com.project.ecommerce.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);   // ✅ ADD THIS


}
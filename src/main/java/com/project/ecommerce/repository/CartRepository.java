package com.project.ecommerce.repository;

import com.project.ecommerce.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    // Get all cart items for a specific user
    List<Cart> findByUserId(String userId);
}
package com.project.ecommerce.repository;

import com.project.ecommerce.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {

    // Get wishlist items for a specific user
    List<Wishlist> findByUserId(String userId);
}
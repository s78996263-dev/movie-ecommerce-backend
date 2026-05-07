package com.project.ecommerce.service;

import com.project.ecommerce.model.Wishlist;
import com.project.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Add item to wishlist
    public Wishlist addWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Get wishlist items for a specific user
    public List<Wishlist> getWishlistItems(String userId) {
        return wishlistRepository.findByUserId(userId);
    }

    // Remove wishlist item
    public void removeWishlist(String id) {
        wishlistRepository.deleteById(id);
    }
}
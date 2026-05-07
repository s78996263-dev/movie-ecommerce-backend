package com.project.ecommerce.controller;

import com.project.ecommerce.model.Wishlist;
import com.project.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("*")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // ✅ Add item to wishlist
    @PostMapping("/add")
    public Wishlist addWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.addWishlist(wishlist);
    }

    // ✅ Get wishlist items for a specific user (MATCHES FRONTEND)
    @GetMapping("/{userId}")
    public List<Wishlist> getWishlist(@PathVariable String userId) {
        return wishlistService.getWishlistItems(userId); // keep your existing service method
    }

    // ✅ Remove item from wishlist
    @DeleteMapping("/{id}")
    public void removeWishlist(@PathVariable String id) {
        wishlistService.removeWishlist(id);
    }
}
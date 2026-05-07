package com.project.ecommerce.controller;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    // ✅ Add item to cart
    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    // ✅ Get cart items by userId (IMPORTANT)
    @GetMapping("/{userId}")
    public List<Cart> getCart(@PathVariable String userId) {
        return cartService.getCartItems(userId);  // ✅ MATCH SERVICE
    }

    // ✅ Remove item
    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable String id) {
        cartService.removeFromCart(id);
    }

    // ✅ Update quantity
    @PutMapping("/update/{id}")
    public void updateQuantity(@PathVariable String id, @RequestParam int change) {
        cartService.updateQuantity(id, change);
    }
}
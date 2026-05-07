package com.project.ecommerce.service;

import com.project.ecommerce.model.Cart;
import com.project.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Add product to cart
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get all cart items for a user
    public List<Cart> getCartItems(String userId) {
        return cartRepository.findByUserId(userId);
    }

    // Remove product from cart
    public void removeFromCart(String id) {
        cartRepository.deleteById(id);
    }

    // ✅ NEW: Update quantity (+ / -)
    public void updateQuantity(String id, int change){

        Cart item = cartRepository.findById(id).orElse(null);

        if(item != null){
            int newQty = item.getQuantity() + change;

            // If quantity becomes 0 → remove item
            if(newQty <= 0){
                cartRepository.deleteById(id);
            } else {
                item.setQuantity(newQty);
                cartRepository.save(item);
            }
        }
    }
}
package com.project.ecommerce.service;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // ✅ GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ✅ GET PRODUCTS BY MOOD
    public List<Product> getProductsByMood(String mood) {
        return productRepository.findByMoodIgnoreCase(mood);
    }

    // ✅ ADD PRODUCT
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // ✅ DELETE PRODUCT
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    // ✅ GET SINGLE PRODUCT (optional but useful)
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }
}
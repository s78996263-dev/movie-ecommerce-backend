package com.project.ecommerce.controller;

import com.project.ecommerce.model.Product;
import com.project.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ PUBLIC
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 🔐 PROTECTED
    @PostMapping
    public Product addProduct(@RequestBody Product product,
                              @RequestHeader("Authorization") String token) {

        // you can validate manually if needed
        return productService.addProduct(product);
    }

    // 🔐 PROTECTED
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id,
                              @RequestHeader("Authorization") String token) {

        productService.deleteProduct(id);
    }

    @GetMapping("/mood/{mood}")
    public List<Product> getByMood(@PathVariable String mood) {
        return productService.getProductsByMood(mood);
    }
}
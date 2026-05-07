package com.project.ecommerce.repository;

import com.project.ecommerce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    // Add ability to find products by mood
    List<Product> findByMood(String mood);

    List<Product> findByMoodIgnoreCase(String mood);
}
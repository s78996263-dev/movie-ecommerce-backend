package com.project.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String mood;
    private double rating;
    private String imageUrl;

    public Product() {}

    public Product(String name, String description, double price, String mood, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.mood = mood;
        this.imageUrl = imageUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    // ✅ THIS FIXES YOUR IMAGE ISSUE
    @JsonProperty("image")
    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }
}
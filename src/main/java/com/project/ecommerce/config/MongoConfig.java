package com.project.ecommerce.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.project.ecommerce.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "ecommerceDB";
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("SPRING_DATA_MONGODB_URI");
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
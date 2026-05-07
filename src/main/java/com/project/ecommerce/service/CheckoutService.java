package com.project.ecommerce.service;

import com.project.ecommerce.model.Checkout;
import com.project.ecommerce.repository.CheckoutRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    public List<Checkout> getCheckoutItems() {
        return checkoutRepository.findAll();
    }

    public Checkout addCheckoutItem(Checkout checkout) {
        return checkoutRepository.save(checkout);
    }

    public void removeCheckoutItem(String id) {
        checkoutRepository.deleteById(id);
    }
}
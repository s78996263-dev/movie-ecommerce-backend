package com.project.ecommerce.controller;

import com.project.ecommerce.model.Checkout;
import com.project.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@CrossOrigin("*")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    public List<Checkout> getCheckoutItems() {
        return checkoutService.getCheckoutItems();
    }

    @PostMapping
    public Checkout addCheckoutItem(@RequestBody Checkout checkout) {
        return checkoutService.addCheckoutItem(checkout);
    }

    @DeleteMapping("/{id}")
    public void removeCheckoutItem(@PathVariable String id) {
        checkoutService.removeCheckoutItem(id);
    }
}

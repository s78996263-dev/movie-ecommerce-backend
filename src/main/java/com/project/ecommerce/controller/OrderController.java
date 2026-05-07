package com.project.ecommerce.controller;

import com.project.ecommerce.model.Order;
import com.project.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ SAVE ORDER
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    // ✅ GET USER ORDER HISTORY
    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable String userId) {
        return orderService.getOrders(userId);
    }

    // ✅ CLEAR ORDER HISTORY
    @DeleteMapping("/{userId}")
    public void clearOrders(@PathVariable String userId) {
        orderService.clearOrders(userId);
    }
}
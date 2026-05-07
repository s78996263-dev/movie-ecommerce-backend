package com.project.ecommerce.service;

import com.project.ecommerce.model.Order;
import com.project.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // ✅ SAVE ORDER (FIXED NAME)
    public Order placeOrder(Order order) {

        order.setOrderDate(new Date());

        // ✅ calculate total automatically
        double total = order.getPrice() * order.getQuantity();
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    // ✅ GET USER ORDERS (FIXED NAME)
    public List<Order> getOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }

    // ✅ CLEAR HISTORY
    public void clearOrders(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        orderRepository.deleteAll(orders);
    }
}

package com.project.ecommerce.controller;

import com.project.ecommerce.model.Payment;
import com.project.ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.makePayment(payment);
    }

    @GetMapping("/history")
    public List<Payment> getPaymentHistory() {
        return paymentService.getPaymentHistory();
    }

    @DeleteMapping("/history")
    public void clearHistory() {
        paymentService.clearHistory();
    }
}
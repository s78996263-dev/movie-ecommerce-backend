package com.project.ecommerce.service;

import com.project.ecommerce.model.Payment;
import com.project.ecommerce.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentHistory() {
        return paymentRepository.findAll();
    }

    public void clearHistory() {
        paymentRepository.deleteAll();
    }
}
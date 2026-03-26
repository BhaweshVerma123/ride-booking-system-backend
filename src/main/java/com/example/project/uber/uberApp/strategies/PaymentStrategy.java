package com.example.project.uber.uberApp.strategies;

import com.example.project.uber.uberApp.entities.Payment;

public interface PaymentStrategy {

    double PLATFORM_COMMISSION = 0.30;

    void processPayment(Payment payment);
}

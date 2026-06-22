package com.zomato.lld.services;

import com.zomato.lld.strategy.payment.PaymentStrategy;

public class PaymentService {

    public boolean processPayment(double amount, PaymentStrategy paymentStrategy) {
        // We delegate the payment processing to whatever strategy was passed in.
        // This class no longer needs to know ABOUT credit cards or UPIs!
        return paymentStrategy.processPayment(amount);
    }
}

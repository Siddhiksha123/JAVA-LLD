package com.zomato.lld.strategy.payment;

public interface PaymentStrategy {
    boolean processPayment(double amount);
}

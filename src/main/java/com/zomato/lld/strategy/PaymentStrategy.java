package com.zomato.lld.strategy;

public interface PaymentStrategy {
    boolean processPayment(double amount);
}

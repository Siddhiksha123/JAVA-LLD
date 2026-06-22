package com.zomato.lld.strategy.payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of Rs." + amount + " via Credit Card.");
        System.out.println("Connecting to Credit Card gateway...");
        // Logic for Credit Card
        return true;
    }
}

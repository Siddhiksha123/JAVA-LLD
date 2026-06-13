package com.zomato.lld.services;

public class PaymentService {
    public boolean processPayment(double amount, String mode) {
        System.out.println("Processing payment of Rs." + amount + " via " + mode);
        // Naive implementation with if-else
        if (mode.equals("CREDIT_CARD")) {
            System.out.println("Connecting to Credit Card gateway...");
            return true;
        } else if (mode.equals("UPI")) {
            System.out.println("Connecting to UPI gateway...");
            return true;
        } else if (mode.equals("CASH_ON_DELIVERY")) {
            System.out.println("Payment will be collected on delivery.");
            return true;
        }
        return false;
    }
}

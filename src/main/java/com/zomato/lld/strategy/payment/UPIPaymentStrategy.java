package com.zomato.lld.strategy.payment;

public class UPIPaymentStrategy implements PaymentStrategy {
    private String upiId;

    public UPIPaymentStrategy(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of Rs." + amount + " via UPI (" + upiId + ").");
        System.out.println("Connecting to UPI gateway...");
        // Logic for UPI
        return true;
    }
}

package com.zomato.lld.strategy.payment;

public class CashOnDeliveryStrategy implements PaymentStrategy {
    private double amountc;

    public CashOnDeliveryStrategy(double amount) {
        this.amountc = amount;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment of Rs." + this.amountc + " will be collected on delivery.");
        return true;
    }
}

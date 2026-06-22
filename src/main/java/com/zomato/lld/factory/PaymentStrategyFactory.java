package com.zomato.lld.factory;

import com.zomato.lld.strategy.payment.CashOnDeliveryStrategy;
import com.zomato.lld.strategy.payment.CreditCardPaymentStrategy;
import com.zomato.lld.strategy.payment.PaymentStrategy;
import com.zomato.lld.strategy.payment.UPIPaymentStrategy;

public class PaymentStrategyFactory {
    
    // The Factory takes the simple String from the frontend and returns the complex strategy object
    public static PaymentStrategy getPaymentStrategy(String paymentType, double amount) {
        if (paymentType.equalsIgnoreCase("UPI")) {
            // Defaulting upi ID here for simplicity, in reality this would also come from user
            return new UPIPaymentStrategy("user@upi");
        } else if (paymentType.equalsIgnoreCase("COD")) {
            return new CashOnDeliveryStrategy(amount);
        } else if (paymentType.equalsIgnoreCase("CREDIT_CARD")) {
            return new CreditCardPaymentStrategy();
        }
        throw new IllegalArgumentException("Invalid payment type: " + paymentType);
    }
}

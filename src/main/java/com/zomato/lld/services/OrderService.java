package com.zomato.lld.services;

import com.zomato.lld.models.Order;
import com.zomato.lld.strategy.PaymentStrategy;

public class OrderService {
    // Tightly coupled dependencies
    private PaymentService paymentService = new PaymentService();
    private DeliveryService deliveryService = new DeliveryService();

    public void placeOrder(Order order, PaymentStrategy paymentStrategy) {
        System.out.println("Placing Order: " + order.getOrderId());
        
        boolean paymentSuccess = paymentService.processPayment(order.getTotalAmount(), paymentStrategy);
        if (paymentSuccess) {
            order.markPreparing();
            System.out.println("Payment Successful. Order status: " + order.getStatus());
            
            // Assign delivery agent explicitly
            deliveryService.assignDeliveryAgent(order);
            
            order.markOutForDelivery();
            System.out.println("Order status: " + order.getStatus());
            
            order.markDelivered();
            System.out.println("Order status: " + order.getStatus());
        } else {
            order.markCancelled();
            System.out.println("Payment Failed. Order Cancelled.");
        }
    }
}

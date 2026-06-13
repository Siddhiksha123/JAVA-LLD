package com.zomato.lld.services;

import com.zomato.lld.models.DeliveryAgent;
import com.zomato.lld.models.Order;

public class DeliveryService {
    public void assignDeliveryAgent(Order order) {
        // Tightly coupled agent assignment
        DeliveryAgent agent = new DeliveryAgent("D1", "John Doe", "9876543210");
        System.out.println("Assigned Delivery Agent: " + agent.getName() + " for Order: " + order.getOrderId());
    }
}

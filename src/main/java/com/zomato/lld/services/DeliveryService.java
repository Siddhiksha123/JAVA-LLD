package com.zomato.lld.services;

import com.zomato.lld.models.DeliveryAgent;
import com.zomato.lld.models.Order;

public class DeliveryService {
    public void assignDeliveryAgent(Order order) {
        DeliveryAgent agent = new DeliveryAgent("D1", "Mohit", "1234567890");
        System.out.println("Assigned Delivery Agent: " + agent.getName() + " for Order: " + order.getOrderId());
    }
}

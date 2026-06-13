package com.zomato.lld.models;

import java.util.List;

public class Order {
    private String orderId;
    private User user;
    private Restaurant restaurant;
    private List<MenuItem> items;
    private String status; // Changed from OrderStatus to String
    private double totalAmount;

    public Order(String orderId, User user, Restaurant restaurant, List<MenuItem> items) {
        this.orderId = orderId;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.status = "PLACED"; // Changed to String
        this.totalAmount = calculateTotal(items);
    }

    private double calculateTotal(List<MenuItem> items) {
        if (items == null) {
            return 0;
        }
        
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        
        return total;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    public List<MenuItem> getItems() { return items; }
    public void setItems(List<MenuItem> items) { this.items = items; }

    public String getStatus() { return status; }
    
    // Replacing generic setter with domain-specific methods
    public void markPreparing() { this.status = "PREPARING"; }
    public void markOutForDelivery() { this.status = "OUT_FOR_DELIVERY"; }
    public void markDelivered() { this.status = "DELIVERED"; }
    public void markCancelled() { this.status = "CANCELLED"; }

    public double getTotalAmount() {
        return totalAmount;
    }
}

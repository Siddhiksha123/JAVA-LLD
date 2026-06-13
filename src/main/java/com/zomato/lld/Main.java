package com.zomato.lld;

import com.zomato.lld.models.MenuItem;
import com.zomato.lld.models.Order;
import com.zomato.lld.models.Restaurant;
import com.zomato.lld.models.User;
import com.zomato.lld.services.OrderService;

import java.util.Arrays;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Zomato App Starting ---");

        // 1. Create User
        User user = new User("U1", "Swati", "swati@example.com", "1234567890");

        // 2. Create Restaurant and Menu
        MenuItem item1 = new MenuItem("M1", "Pizza", 250.0);
        MenuItem item2 = new MenuItem("M2", "Burger", 150.0);
        Restaurant restaurant = new Restaurant("R1", "Dominos", "Downtown", Arrays.asList(item1, item2));

        // 3. Create Order
        Order order = new Order(UUID.randomUUID().toString(), user, restaurant, Arrays.asList(item1, item2));

        // 4. Place Order via Service
        OrderService orderService = new OrderService();
        orderService.placeOrder(order, "UPI"); // Passing String directly
        
        System.out.println("--- Order Processing Complete ---");
    }
}

package com.zomato.lld;

import com.zomato.lld.models.MenuItem;
import com.zomato.lld.models.Order;
import com.zomato.lld.models.Restaurant;
import com.zomato.lld.models.User;
import com.zomato.lld.services.OrderService;
import com.zomato.lld.strategy.CashOnDeliveryStrategy;
import com.zomato.lld.strategy.PaymentStrategy;
import com.zomato.lld.strategy.UPIPaymentStrategy;

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
        Restaurant restaurant = new Restaurant("R1", "Dominos", "Mumbai", Arrays.asList(item1, item2));

        // 3. Create Order
        Order order = new Order(UUID.randomUUID().toString(), user, restaurant, Arrays.asList(item1, item2));

        // 4. Place Order via Service
        OrderService orderService = new OrderService();

        // Use the Strategy Pattern! We pass in the specific behavior we want.
        PaymentStrategy upiStrategy = new UPIPaymentStrategy("swati@upi");
        orderService.placeOrder(order, upiStrategy);

        // 5. Place another order with different payment method
        Order order2 = new Order(UUID.randomUUID().toString(), user, restaurant, Arrays.asList(item1));
        PaymentStrategy CODStrategy = new CashOnDeliveryStrategy(2000);
        orderService.placeOrder(order2, CODStrategy);

    }
}

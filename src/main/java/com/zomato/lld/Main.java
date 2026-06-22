package com.zomato.lld;

import com.zomato.lld.models.MenuItem;
import com.zomato.lld.models.Order;
import com.zomato.lld.models.Restaurant;
import com.zomato.lld.models.User;
import com.zomato.lld.services.OrderService;
import com.zomato.lld.strategy.payment.CashOnDeliveryStrategy;
import com.zomato.lld.strategy.payment.CreditCardPaymentStrategy;
import com.zomato.lld.strategy.payment.PaymentStrategy;
import com.zomato.lld.strategy.payment.UPIPaymentStrategy;

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
        Order order1 = new Order(UUID.randomUUID().toString(), user, restaurant, Arrays.asList(item1, item2));

        // 4. Place Order via Service
        OrderService orderService = new OrderService();

        // Simulate frontend input for Order 1
        String userInput1 = "UPI";
        PaymentStrategy strategy1 = null;
        
        if (userInput1.equals("UPI")) {
            strategy1 = new UPIPaymentStrategy("swati@upi");
        } else if (userInput1.equals("COD")) {
            strategy1 = new CashOnDeliveryStrategy(order1.getTotalAmount());
        } else if (userInput1.equals("CREDIT_CARD")) {
            strategy1 = new CreditCardPaymentStrategy();
        }
        
        orderService.placeOrder(order1, strategy1);

        // 5. Place another order with different payment method
        Order order2 = new Order(UUID.randomUUID().toString(), user, restaurant, Arrays.asList(item1));
        
        // Simulate frontend input for Order 2
        String userInput2 = "COD";
        PaymentStrategy strategy2 = null;
        
        if (userInput2.equals("UPI")) {
            strategy2 = new UPIPaymentStrategy("swati@upi");
        } else if (userInput2.equals("COD")) {
            strategy2 = new CashOnDeliveryStrategy(order2.getTotalAmount());
        } else if (userInput2.equals("CREDIT_CARD")) {
            strategy2 = new CreditCardPaymentStrategy();
        }
        
        orderService.placeOrder(order2, strategy2);

    }
}

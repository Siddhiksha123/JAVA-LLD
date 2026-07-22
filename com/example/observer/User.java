package com.example.observer;

import java.util.HashMap;
import java.util.Map;

public class User implements Observer {

    private final String userId;
    private final String name;
    private final String email;

    // Positive -> other user owes this user
    // Negative -> this user owes the other user
    private final Map<String, Double> balances = new HashMap<>();

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Double> getBalances() {
        return balances;
    }

    public void updateBalance(String otherUserId, double amount) {
        double currentBalance = balances.getOrDefault(otherUserId, 0.0);
        balances.put(otherUserId, currentBalance + amount);
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            return userId.equals(other.userId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
package com.example.observer;

//  Represents a single user's share within an Expense.
//  Model class - holds data only, no business logic.

public class Split {

    private final String userId;
    private final double amount;

    public Split(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Split{userId='" + userId + "', amount=" + amount + "}";
    }
}

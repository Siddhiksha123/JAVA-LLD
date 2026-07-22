package com.example.observer;

import java.util.List;

//   Represents a single expense within a Group.
//   Composed of multiple Split objects (1..* relationship) -
//   if the Expense is deleted, its Splits have no meaning on their own.

public class Expense {

    private final String id;
    private final String description;
    private final double amount;
    private final String paidByUserId;
    private final List<Split> splits;
    private final String groupId;

    public Expense(String id, String description, double amount,
            String paidByUserId, List<Split> splits, String groupId) {
        validateSplits(amount, splits);
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paidByUserId = paidByUserId;
        this.splits = splits;
        this.groupId = groupId;
    }

    // Invariant: sum of all split amounts must equal the total expense amount.
    private void validateSplits(double amount, List<Split> splits) {
        double sum = 0;

        for (Split split : splits) {
            sum += split.getAmount();
        }

        if (sum != amount) {
            throw new IllegalArgumentException("Split amounts do not match expense amount.");
        }
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public String getGroupId() {
        return groupId;
    }
}

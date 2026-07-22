package com.example.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

    private final String groupId;
    private final String name;
    private final List<User> members;
    private final Map<String, Expense> expenses;
    private final Map<String, Double> balances;

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new HashMap<>();
        this.balances = new HashMap<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public Map<String, Double> getBalances() {
        return balances;
    }

    public Map<String, Expense> getExpenses() {
        return expenses;
    }

    public void addMember(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        if (!members.contains(user)) {
            members.add(user);
            balances.put(user.getUserId(), 0.0);
        }
    }

    public void removeMember(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null.");
        }

        double balance = balances.getOrDefault(userId, 0.0);
        if (Math.abs(balance) > 1e-9) {
            throw new IllegalStateException("User has pending balance.");
        }

        members.removeIf(member -> member.getUserId().equals(userId));
        balances.remove(userId);
    }

    public void addExpense(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null.");
        }

        expenses.put(expense.getId(), expense);

        String payerId = expense.getPaidByUserId();
        for (Split split : expense.getSplits()) {
            if (!split.getUserId().equals(payerId)) {
                updateGroupBal(split.getUserId(), payerId, split.getAmount());
            }
        }

        notifyMembers("New Expense Added: " + expense.getDescription());
    }

    public void updateGroupBal(String fromId, String toId, double amount) {
        if (fromId == null || toId == null) {
            throw new IllegalArgumentException("User ids cannot be null.");
        }

        double fromBalance = balances.getOrDefault(fromId, 0.0);
        balances.put(fromId, fromBalance - amount);

        double toBalance = balances.getOrDefault(toId, 0.0);
        balances.put(toId, toBalance + amount);
    }

    public void settlePayment(String from, String to, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        updateGroupBal(to, from, amount);
        notifyMembers(from + " paid " + amount + " to " + to);
    }

    private void notifyMembers(String message) {
        for (User user : members) {
            user.update(message);
        }
    }
}

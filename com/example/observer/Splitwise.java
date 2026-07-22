package com.example.observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Splitwise {

    private static Splitwise instance = new Splitwise();

    private Map<String, User> users;
    private Map<String, Group> groups;
    private Map<String, Expense> expenses;

    // Stores balances for individual (non-group) expenses
    private Map<String, Map<String, Double>> individualBalances;

    private DebtSimplifier debtSimplifier;

    private Splitwise() {
        users = new HashMap<>();
        groups = new HashMap<>();
        expenses = new HashMap<>();
        individualBalances = new HashMap<>();
        debtSimplifier = new DebtSimplifier();
    }

    public static Splitwise getInstance() {
        return instance;
    }

    // Register a new user
    public User registerUser(String userId, String name, String email) {

        User user = new User(userId, name, email);
        users.put(userId, user);

        return user;
    }

    // Create a new group
    public Group createGroup(String groupName, String creatorUserId) {

        User creator = users.get(creatorUserId);

        if (creator == null) {
            throw new IllegalArgumentException("User not found.");
        }

        String groupId = UUID.randomUUID().toString();

        Group group = new Group(groupId, groupName);

        group.addMember(creator);

        groups.put(groupId, group);

        return group;
    }

    // Add expense inside a group
    public Expense addGroupExpense(String groupId,
            String description,
            double amount,
            String paidByUserId,
            SplitType splitType,
            List<String> userIds,
            List<Double> values) {

        Group group = groups.get(groupId);

        if (group == null) {
            throw new IllegalArgumentException("Group not found.");
        }

        SplitStrategy strategy = SplitFactory.getSplitStrategy(splitType);

        List<Split> splits = strategy.calcSplit(amount, userIds, values);

        String expenseId = UUID.randomUUID().toString();

        Expense expense = new Expense(
                expenseId,
                description,
                amount,
                paidByUserId,
                splits,
                groupId);

        expenses.put(expenseId, expense);

        group.addExpense(expense);

        return expense;
    }

    // Add expense between two users (not in a group)
    public void addIndividualExpense(String fromUserId,
            String toUserId,
            double amount) {

        if (!individualBalances.containsKey(fromUserId)) {
            individualBalances.put(fromUserId, new HashMap<>());
        }

        if (!individualBalances.containsKey(toUserId)) {
            individualBalances.put(toUserId, new HashMap<>());
        }

        Map<String, Double> fromMap = individualBalances.get(fromUserId);
        Map<String, Double> toMap = individualBalances.get(toUserId);

        fromMap.put(
                toUserId,
                fromMap.getOrDefault(toUserId, 0.0) - amount);

        toMap.put(
                fromUserId,
                toMap.getOrDefault(fromUserId, 0.0) + amount);
    }

    // Settle payment between two users
    public void settleIndividualPayment(String fromUserId,
            String toUserId,
            double amount) {

        addIndividualExpense(toUserId, fromUserId, amount);
    }

    // Simplify group debts
    public List<DebtSimplifier.Transaction> simplifyGroupDebts(String groupId) {

        Group group = groups.get(groupId);

        if (group == null) {
            throw new IllegalArgumentException("Group not found.");
        }

        return debtSimplifier.simplifyDebt(group.getBalances());
    }

    public Group getGroup(String groupId) {
        return groups.get(groupId);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
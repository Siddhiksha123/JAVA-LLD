package com.example.observer;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Splitwise splitwise = Splitwise.getInstance();

        // Register Users
        splitwise.registerUser("u-swati", "Swati", "swati@example.com");
        splitwise.registerUser("u-shubham", "Shubham", "shubham@example.com");
        splitwise.registerUser("u-shivani", "Shivani", "shivani@example.com");

        // Create Group
        Group group = splitwise.createGroup("Goa Trip", "u-swati");

        group.addMember(splitwise.getUser("u-shubham"));
        group.addMember(splitwise.getUser("u-shivani"));

        // Users sharing the expense
        List<String> users = Arrays.asList(
                "u-swati",
                "u-shubham",
                "u-shivani");

        // Add Expense
        splitwise.addGroupExpense( group.getGroupId(),"Goa Dinner",
                900,
                "u-swati",
                SplitType.EQUAL,
                users,
                null);

        // Display balances
        System.out.println("Balances:");
        System.out.println(group.getBalances());

        // Shubham pays Swati
        group.settlePayment("u-shubham", "u-swati", 300);

        System.out.println("\nBalances after settlement:");
        System.out.println(group.getBalances());

        // Simplify debts
        List<DebtSimplifier.Transaction> transactions = splitwise.simplifyGroupDebts(group.getGroupId());

        System.out.println("\nSimplified Transactions:");
        System.out.println(transactions);
    }
}

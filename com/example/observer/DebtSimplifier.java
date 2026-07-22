package com.example.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DebtSimplifier {

    public static class Transaction {

        public final String fromUserId;
        public final String toUserId;
        public final double amount;

        public Transaction(String fromUserId, String toUserId, double amount) {
            this.fromUserId = fromUserId;
            this.toUserId = toUserId;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return fromUserId + " pays " + toUserId + " -> " + amount;
        }
    }

    public List<Transaction> simplifyDebt(Map<String, Double> balances) {

        List<Transaction> transactions = new ArrayList<>();

        List<Map.Entry<String, Double>> creditors = new ArrayList<>();
        List<Map.Entry<String, Double>> debtors = new ArrayList<>();

        // Separate creditors and debtors
        for (var entry : balances.entrySet()) {

            if (entry.getValue() > 0) {
                creditors.add(entry);
            } else if (entry.getValue() < 0) {
                debtors.add(entry);
            }
        }

        // Match debtors with creditors
        for (Map.Entry<String, Double> debtor : debtors) {

            double debt = -debtor.getValue();

            for (Map.Entry<String, Double> creditor : creditors) {

                double credit = creditor.getValue();

                if (debt == 0 || credit == 0) {
                    continue;
                }

                double amount = Math.min(debt, credit);

                transactions.add(
                        new Transaction(
                                debtor.getKey(),
                                creditor.getKey(),
                                amount));

                debt -= amount;
                creditor.setValue(credit - amount);
            }
        }

        return transactions;
    }
}
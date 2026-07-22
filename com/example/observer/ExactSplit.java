package com.example.observer;

import java.util.ArrayList;
import java.util.List;

public class ExactSplit implements SplitStrategy {

    @Override
    public List<Split> calcSplit(double totalAmount, List<String> userIds, List<Double> values) {
        if (userIds == null || values == null || userIds.size() != values.size()) {
            throw new IllegalArgumentException("Invalid input.");
        }

        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }

        if (Math.abs(sum - totalAmount) > 1e-9) {
            throw new IllegalArgumentException("Split values do not match the total amount.");
        }

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < userIds.size(); i++) {
            splits.add(new Split(userIds.get(i), values.get(i)));
        }

        return splits;
    }
}

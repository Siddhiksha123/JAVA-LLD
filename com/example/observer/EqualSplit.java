package com.example.observer;

import java.util.ArrayList;
import java.util.List;

public class EqualSplit implements SplitStrategy {

    @Override
    public List<Split> calcSplit(double totalAmount, List<String> userIds, List<Double> values) {
        if (userIds == null || userIds.isEmpty()) {
            throw new IllegalArgumentException("User list cannot be empty.");
        }

        double share = totalAmount / userIds.size();
        List<Split> splits = new ArrayList<>();

        for (String userId : userIds) {
            splits.add(new Split(userId, share));
        }

        return splits;
    }
}

package com.example.observer;

import java.util.List;

//  Strategy interface for calculating how an expense amount is
//  split across a set of users. Concrete strategies: EqualSplit,
//  ExactSplit, (future) PercentageSplit.

public interface SplitStrategy {
    List<Split> calcSplit(double totalAmount, List<String> userIds, List<Double> values);
}

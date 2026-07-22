package com.example.observer;

public class SplitFactory {

    public static SplitStrategy getSplitStrategy(SplitType type) {
        switch (type) {
            case EQUAL:
                return new EqualSplit();
            case EXACT:
                return new ExactSplit();
            case PERCENTAGE:
                throw new UnsupportedOperationException("PercentageSplit not implemented yet");
            default:
                throw new IllegalArgumentException("Unknown split type: " + type);
        }
    }
}

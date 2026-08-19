package com.hdfclife.model;

public enum Urgency {
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    private final int priority;

    Urgency(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}

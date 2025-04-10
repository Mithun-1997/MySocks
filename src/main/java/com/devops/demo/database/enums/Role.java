package com.devops.demo.database.enums;

public enum Role {
    USER(1), ADMIN(2), SUPERADMIN(4);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Role fromValue(int value) {
        for (Role r : Role.values()) {
            if (r.value == value) return r;
        }
        throw new IllegalArgumentException("Invalid role value: " + value);
    }
}

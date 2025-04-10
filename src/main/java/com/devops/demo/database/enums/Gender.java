package com.devops.demo.database.enums;

public enum Gender {
    MALE(1), FEMALE(2), OTHER(3);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Gender fromValue(int value) {
        for (Gender g : Gender.values()) {
            if (g.value == value) return g;
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}

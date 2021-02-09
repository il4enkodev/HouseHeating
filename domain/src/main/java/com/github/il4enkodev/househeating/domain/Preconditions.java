package com.github.il4enkodev.househeating.domain;

public final class Preconditions {

    public static <T> T requireNotNull(T instance) {
        return requireNotNull(instance, null);
    }

    public static <T> T requireNotNull(T instance, String name) {
        if (instance == null)
            throw new NullPointerException(name != null ? name + " is null": "");
        return instance;
    }

    private Preconditions() {
    }
}

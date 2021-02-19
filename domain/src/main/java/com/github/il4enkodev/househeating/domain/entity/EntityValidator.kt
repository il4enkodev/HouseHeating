package com.github.il4enkodev.househeating.domain.entity;

import com.github.il4enkodev.househeating.domain.exception.IllegalEntityStateException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@FunctionalInterface
public interface EntityValidator<T extends Entity<?>> extends Function<T, T> {

    void validate(T entity) throws IllegalEntityStateException;

    @Override
    default T apply(@NonNull T t) {
        validate(t);
        return t;
    }

    static <T extends Entity<?>> EntityValidator<T> hasId() {
        return entity -> {
            if (entity.id() == null)
                throw new IllegalEntityStateException("Entity without id is not allowed");
        };
    }

    static <T extends Entity<?>> EntityValidator<T> allowAll() {
        return entity -> { /* noop */ };
    }
}

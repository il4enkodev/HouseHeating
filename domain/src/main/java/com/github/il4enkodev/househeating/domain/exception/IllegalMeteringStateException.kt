package com.github.il4enkodev.househeating.domain.exception;

import com.github.il4enkodev.househeating.domain.entity.metering.Metering;

public class IllegalMeteringStateException extends IllegalEntityStateException {

    private final Metering<?> metering;

    public IllegalMeteringStateException(Metering<?> metering) {
        this.metering = metering;
    }

    public IllegalMeteringStateException(String s, Metering<?> metering) {
        super(s);
        this.metering = metering;
    }

    public Metering<?> getMetering() {
        return metering;
    }
}

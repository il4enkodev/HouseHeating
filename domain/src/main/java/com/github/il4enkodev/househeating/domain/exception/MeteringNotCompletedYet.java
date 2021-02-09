package com.github.il4enkodev.househeating.domain.exception;

import com.github.il4enkodev.househeating.domain.entity.metering.Metering;

public class MeteringNotCompletedYet extends IllegalMeteringStateException {

    public MeteringNotCompletedYet(Metering<?> metering) {
        super(metering);
    }
}

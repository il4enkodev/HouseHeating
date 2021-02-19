package com.github.il4enkodev.househeating.domain.exception;

import com.github.il4enkodev.househeating.domain.entity.metering.Metering;

public class MeteringNotStartedYet extends IllegalMeteringStateException {

    public MeteringNotStartedYet(Metering<?> metering) {
        super(metering);
    }
}

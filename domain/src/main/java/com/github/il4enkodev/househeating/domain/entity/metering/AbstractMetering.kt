package com.github.il4enkodev.househeating.domain.entity.metering;

import javax.annotation.Nonnull;

import static com.github.il4enkodev.househeating.domain.Preconditions.requireNotNull;

abstract class AbstractMetering<U> implements Metering<U> {

    private final String id;
    private final MeterReadings readingsOnStart;

    AbstractMetering(@Nonnull String id, @Nonnull MeterReadings readingsOnStart) {
        this.id = requireNotNull(id, "id");
        this.readingsOnStart = requireNotNull(readingsOnStart, "readingsOnStart");
    }

    @Nonnull
    @Override
    public final String id() {
        return id;
    }

    @Nonnull
    @Override
    public final MeterReadings start() {
        return readingsOnStart;
    }

    @Override
    public final boolean isStarted() {
        return true;
    }

    @Override
    public final boolean isContinuing() {
        return !isCompleted();
    }


}

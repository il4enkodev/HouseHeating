package com.github.il4enkodev.househeating.domain.entity.metering;

import com.github.il4enkodev.househeating.domain.exception.MeteringNotStartedYet;

import java.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PredictedMetering<P> extends AbstractMetering<P> {

    PredictedMetering(@Nonnull String id, @Nonnull MeterReadings readingsOnStart) {
        super(id, readingsOnStart);
        throw new UnsupportedOperationException("For future use");
    }

    @Nonnull
    @Override
    public MeterReadings end() {
        throw new MeteringNotStartedYet(this);
    }

    @Nullable
    @Override
    public P usage() {
        return null;
    }

    @Nonnull
    @Override
    public Duration duration() {
        return null;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}

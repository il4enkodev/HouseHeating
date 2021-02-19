package com.github.il4enkodev.househeating.domain.entity.metering;

import com.github.il4enkodev.househeating.domain.exception.MeteringNotCompletedYet;

import java.time.Duration;
import java.time.ZonedDateTime;

import javax.annotation.Nonnull;

public class ContinuingMetering extends AbstractMetering<Double> {

    public ContinuingMetering(@Nonnull String id, @Nonnull MeterReadings readingsOnStart) {
        super(id, readingsOnStart);
    }

    @Nonnull
    @Override
    public final MeterReadings end() {
        throw new MeteringNotCompletedYet(this);
    }

    @Override
    public final boolean isCompleted() {
        return false;
    }

    @Override
    public Double usage() {
        return null;
    }

    @Nonnull
    @Override
    public Duration duration() {
        final ZonedDateTime startTime = start().getTime();
        return Duration.between(startTime,
                ZonedDateTime.now().withZoneSameInstant(startTime.getZone()));
    }
}

package com.github.il4enkodev.househeating.domain.entity.metering;

import java.time.Duration;
import java.time.ZonedDateTime;

import javax.annotation.Nonnull;

import static com.github.il4enkodev.househeating.domain.Preconditions.requireNotNull;

public class CompletedMetering extends AbstractMetering<Double> {

    private final MeterReadings readingsOnComplete;

    CompletedMetering(@Nonnull String id,
                      @Nonnull MeterReadings readingsOnStart,
                      @Nonnull MeterReadings readingsOnComplete) {
        super(id, readingsOnStart);
        this.readingsOnComplete = requireNotNull(readingsOnComplete, "readingsOnComplete");
    }

    @Nonnull
    @Override
    public Double usage() {
        return end().getValue() - start().getValue();
    }

    @Nonnull
    @Override
    public Duration duration() {
        final ZonedDateTime start = start().getTime();
        final ZonedDateTime complete = end().getTime();
        // TODO Check same TZ?
        return Duration.between(complete, start);
    }

    @Nonnull
    @Override
    public final MeterReadings end() {
        return readingsOnComplete;
    }

    @Override
    public final boolean isCompleted() {
        return true;
    }
}

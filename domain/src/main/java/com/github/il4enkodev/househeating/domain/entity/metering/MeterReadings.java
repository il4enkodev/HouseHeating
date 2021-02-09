package com.github.il4enkodev.househeating.domain.entity.metering;

import java.time.ZonedDateTime;
import java.util.Objects;

import static com.github.il4enkodev.househeating.domain.Preconditions.requireNotNull;

public final class MeterReadings {

    private final ZonedDateTime time;
    private final double value;

    public MeterReadings(ZonedDateTime time, double value) {
        this.time = requireNotNull(time, "time");
        this.value = value;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeterReadings)) return false;
        MeterReadings that = (MeterReadings) o;
        return Double.compare(that.value, value) == 0 &&
                time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, value);
    }
}

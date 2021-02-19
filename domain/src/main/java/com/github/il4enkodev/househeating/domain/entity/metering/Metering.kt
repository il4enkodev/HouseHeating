package com.github.il4enkodev.househeating.domain.entity.metering;

import com.github.il4enkodev.househeating.domain.entity.Entity;
import com.github.il4enkodev.househeating.domain.exception.MeteringNotStartedYet;

import java.time.Duration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public interface Metering<U> extends Entity<String> {

    @Nonnull
    MeterReadings start();

    @Nonnull
    MeterReadings end();

    @Nullable
    U usage();

    @Nonnull
    Duration duration();

    boolean isStarted();

    boolean isCompleted();

    boolean isContinuing();

    @SuppressWarnings("unchecked")
    static <U> Metering<U> notStarted() {
        return (Metering<U>) NOT_STARTED;
    }

    Metering<?> NOT_STARTED = new Metering<Void>() {

        @Nullable
        @Override
        public String id() {
            return null;
        }

        @Nonnull
        @Override
        public MeterReadings start() {
            throw new com.github.il4enkodev.househeating.domain.exception.MeteringNotStartedYet(this);
        }

        @Nonnull
        @Override
        public MeterReadings end() {
            throw new com.github.il4enkodev.househeating.domain.exception.MeteringNotStartedYet(this);
        }

        @Nullable
        @Override
        public Void usage() {
            return null;
        }

        @Nonnull
        @Override
        public Duration duration() {
            throw new MeteringNotStartedYet(this);
        }

        @Override
        public boolean isStarted() {
            return false;
        }

        @Override
        public boolean isCompleted() {
            return false;
        }

        @Override
        public boolean isContinuing() {
            return false;
        }

        @Override
        public String toString() {
            return "Metering <Not Started>";
        }
    };

}

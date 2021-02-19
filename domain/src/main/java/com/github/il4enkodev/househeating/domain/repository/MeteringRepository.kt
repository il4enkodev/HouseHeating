package com.github.il4enkodev.househeating.domain.repository;

import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering;
import com.github.il4enkodev.househeating.domain.entity.metering.ContinuingMetering;
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings;
import com.github.il4enkodev.househeating.domain.entity.metering.Metering;

import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface MeteringRepository {

    Single<CompletedMetering> create(MeterReadings onStart, MeterReadings onComplete);

    Observable<List<CompletedMetering>> observe();

    Observable<Metering<Double>> current();

    Single<ContinuingMetering> startMetering(MeterReadings readings);

    Single<CompletedMetering> stopMetering(MeterReadings readings);

    Single<CompletedMetering> get(String id);

    Single<CompletedMetering> update(String id,
                                     @Nullable MeterReadings onStart,
                                     @Nullable MeterReadings onComplete);

    Completable delete(String id);

}

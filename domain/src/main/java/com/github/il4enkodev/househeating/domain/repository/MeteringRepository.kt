package com.github.il4enkodev.househeating.domain.repository

import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering
import com.github.il4enkodev.househeating.domain.entity.metering.ContinuingMetering
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings
import com.github.il4enkodev.househeating.domain.entity.metering.Metering
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MeteringRepository {
    fun create(onStart: MeterReadings?, onComplete: MeterReadings?): Single<CompletedMetering>
    fun observe(): Observable<List<CompletedMetering>>
    fun current(): Observable<Metering<Double>>
    fun startMetering(readings: MeterReadings): Single<ContinuingMetering>
    fun stopMetering(readings: MeterReadings): Single<CompletedMetering>
    operator fun get(id: String): Single<CompletedMetering>
    fun update(id: String,
               onStart: MeterReadings?,
               onComplete: MeterReadings?): Single<CompletedMetering>

    fun delete(id: String): Completable
}
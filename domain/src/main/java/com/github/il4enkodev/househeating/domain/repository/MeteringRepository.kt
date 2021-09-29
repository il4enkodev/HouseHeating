package com.github.il4enkodev.househeating.domain.repository

import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering
import com.github.il4enkodev.househeating.domain.entity.metering.ContinuingMetering
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings
import com.github.il4enkodev.househeating.domain.entity.metering.Metering
import kotlinx.coroutines.flow.Flow

interface MeteringRepository {
    fun current(): Flow<Metering<Double>>
    fun observe(): Flow<List<CompletedMetering>>
    suspend fun create(onStart: MeterReadings?, onComplete: MeterReadings?): CompletedMetering
    suspend fun startMetering(readings: MeterReadings): ContinuingMetering
    suspend fun stopMetering(readings: MeterReadings): CompletedMetering
    suspend fun get(id: String): CompletedMetering
    suspend fun update(id: String,
               onStart: MeterReadings?,
               onComplete: MeterReadings?): CompletedMetering

    suspend fun delete(id: String)
}
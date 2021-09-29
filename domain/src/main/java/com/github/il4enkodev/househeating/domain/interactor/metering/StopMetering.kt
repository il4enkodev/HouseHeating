package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.di.IoDispatcher
import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings
import com.github.il4enkodev.househeating.domain.interactor.UseCase
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class StopMetering @Inject internal constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val meteringRepository: MeteringRepository
) : UseCase<MeterReadings, CompletedMetering>(dispatcher) {

    override suspend fun execute(arguments: MeterReadings): CompletedMetering {
        return meteringRepository.stopMetering(arguments)
    }
}
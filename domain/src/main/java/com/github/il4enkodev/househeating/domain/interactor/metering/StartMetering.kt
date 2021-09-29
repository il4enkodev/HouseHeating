package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.di.IoDispatcher
import com.github.il4enkodev.househeating.domain.entity.metering.ContinuingMetering
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings
import com.github.il4enkodev.househeating.domain.interactor.UseCase
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class StartMetering @Inject internal constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val meteringRepository: MeteringRepository
) : UseCase<MeterReadings, ContinuingMetering>(dispatcher) {

    override suspend fun execute(arguments: MeterReadings): ContinuingMetering {
        return meteringRepository.startMetering(arguments)
    }
}
package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.di.IoDispatcher
import com.github.il4enkodev.househeating.domain.entity.metering.Metering
import com.github.il4enkodev.househeating.domain.interactor.FlowUseCase
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CurrentMetering @Inject internal constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val meteringRepository: MeteringRepository
) : FlowUseCase<Nothing, Metering<Double>>(dispatcher) {

    override suspend fun execute(arguments: Nothing): Flow<Metering<Double>> {
        return meteringRepository.current()
    }
}
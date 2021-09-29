package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.di.IoDispatcher
import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering
import com.github.il4enkodev.househeating.domain.interactor.UseCase
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class EditMetering @Inject internal constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val meteringRepository: MeteringRepository
) : UseCase<EditMetering.Arguments, CompletedMetering, >(dispatcher) {

    override suspend fun execute(arguments: Arguments): CompletedMetering {
        TODO("Not yet implemented")
    }

    class Arguments
}
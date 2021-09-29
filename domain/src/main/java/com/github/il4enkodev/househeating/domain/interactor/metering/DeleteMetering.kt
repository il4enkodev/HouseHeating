package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.di.IoDispatcher
import com.github.il4enkodev.househeating.domain.interactor.UseCase
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteMetering @Inject internal constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val meteringRepository: MeteringRepository
) : UseCase<String, Unit>(dispatcher) {

    override suspend fun execute(arguments: String) {
        meteringRepository.delete(arguments)
    }
}
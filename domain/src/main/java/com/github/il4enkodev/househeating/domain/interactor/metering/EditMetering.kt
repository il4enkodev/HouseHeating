package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher
import com.github.il4enkodev.househeating.domain.interactor.UseCaseSingle
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import io.reactivex.Single
import javax.inject.Inject

class EditMetering @Inject internal constructor(
        schedulerSwitcher: SchedulerSwitcher<CompletedMetering>,
        private val meteringRepository: MeteringRepository
) : UseCaseSingle<CompletedMetering, EditMetering.Arguments>(schedulerSwitcher) {

    override fun source(arguments: Arguments): Single<CompletedMetering> {
        throw UnsupportedOperationException("Not implemented")
    }

    class Arguments
}
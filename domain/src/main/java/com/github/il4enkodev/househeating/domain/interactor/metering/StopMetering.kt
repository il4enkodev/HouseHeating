package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher
import com.github.il4enkodev.househeating.domain.interactor.UseCaseSingle
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import io.reactivex.Single
import javax.annotation.Nonnull
import javax.inject.Inject

class StopMetering @Inject internal constructor(
        schedulerSwitcher: SchedulerSwitcher<CompletedMetering>,
        private val meteringRepository: MeteringRepository
) : UseCaseSingle<CompletedMetering, MeterReadings>(schedulerSwitcher) {

    @Nonnull
    override fun source(arguments: MeterReadings): Single<CompletedMetering> {
        return meteringRepository.stopMetering(arguments)
    }
}
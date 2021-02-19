package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.entity.metering.ContinuingMetering
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher
import com.github.il4enkodev.househeating.domain.interactor.UseCaseSingle
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import io.reactivex.Single
import javax.inject.Inject

class StartMetering @Inject internal constructor(
        schedulerSwitcher: SchedulerSwitcher<ContinuingMetering>,
        private val meteringRepository: MeteringRepository
) : UseCaseSingle<ContinuingMetering, MeterReadings>(schedulerSwitcher) {

    override fun source(arguments: MeterReadings): Single<ContinuingMetering> {
        return meteringRepository.startMetering(arguments)
    }
}
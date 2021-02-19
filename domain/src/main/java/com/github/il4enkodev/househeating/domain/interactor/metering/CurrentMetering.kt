package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.entity.metering.Metering
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher
import com.github.il4enkodev.househeating.domain.interactor.UseCaseObservable
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import io.reactivex.Observable
import javax.inject.Inject

class CurrentMetering @Inject internal constructor(
        schedulerSwitcher: SchedulerSwitcher<Metering<Double>>,
        private val meteringRepository: MeteringRepository
) : UseCaseObservable<Metering<Double>, Nothing>(schedulerSwitcher) {

    override fun source(arguments: Nothing): Observable<Metering<Double>> {
        return meteringRepository.current()
    }
}
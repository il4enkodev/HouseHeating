package com.github.il4enkodev.househeating.domain.interactor.metering

import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher
import com.github.il4enkodev.househeating.domain.interactor.UseCaseCompletable
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteMetering @Inject internal constructor(
        schedulerSwitcher: SchedulerSwitcher<*>,
        private val meteringRepository: MeteringRepository
) : UseCaseCompletable<String>(schedulerSwitcher) {

    override fun source(arguments: String): Completable {
        return meteringRepository.delete(arguments)
    }
}
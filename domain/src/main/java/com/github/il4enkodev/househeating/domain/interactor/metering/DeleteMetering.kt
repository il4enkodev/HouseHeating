package com.github.il4enkodev.househeating.domain.interactor.metering;

import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher;
import com.github.il4enkodev.househeating.domain.interactor.UseCaseCompletable;
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class DeleteMetering extends UseCaseCompletable<String> {

    private final MeteringRepository meteringRepository;

    @Inject
    DeleteMetering(SchedulerSwitcher<?> schedulerSwitcher, MeteringRepository meteringRepository) {
        super(schedulerSwitcher);
        this.meteringRepository = meteringRepository;
    }

    @Override
    protected Completable source(String arguments) {
        return meteringRepository.delete(arguments);
    }
}

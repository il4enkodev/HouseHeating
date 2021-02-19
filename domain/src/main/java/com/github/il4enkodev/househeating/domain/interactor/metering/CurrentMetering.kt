package com.github.il4enkodev.househeating.domain.interactor.metering;

import com.github.il4enkodev.househeating.domain.entity.metering.Metering;
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher;
import com.github.il4enkodev.househeating.domain.interactor.UseCaseObservable;
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CurrentMetering extends UseCaseObservable<Metering<Double>, Void> {

    private final MeteringRepository meteringRepository;

    @Inject
    CurrentMetering(SchedulerSwitcher<Metering<Double>> schedulerSwitcher,
                    MeteringRepository meteringRepository) {
        super(schedulerSwitcher);
        this.meteringRepository = meteringRepository;
    }

    @Override
    protected Observable<Metering<Double>> source(Void arguments) {
        return meteringRepository.current();
    }
}

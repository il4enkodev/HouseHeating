package com.github.il4enkodev.househeating.domain.interactor.metering;

import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering;
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings;
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher;
import com.github.il4enkodev.househeating.domain.interactor.UseCaseSingle;
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Single;

public class StopMetering extends UseCaseSingle<CompletedMetering, MeterReadings> {

    private final MeteringRepository meteringRepository;

    @Inject
    StopMetering(SchedulerSwitcher<CompletedMetering> schedulerSwitcher,
                 MeteringRepository meteringRepository) {
        super(schedulerSwitcher);
        this.meteringRepository = meteringRepository;
    }

    @Nonnull
    @Override
    protected Single<CompletedMetering> source(MeterReadings readings) {
        return meteringRepository.stopMetering(readings);
    }
}

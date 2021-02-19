package com.github.il4enkodev.househeating.domain.interactor.metering;

import com.github.il4enkodev.househeating.domain.entity.metering.ContinuingMetering;
import com.github.il4enkodev.househeating.domain.entity.metering.MeterReadings;
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher;
import com.github.il4enkodev.househeating.domain.interactor.UseCaseSingle;
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Single;

public class StartMetering extends UseCaseSingle<ContinuingMetering, MeterReadings> {

    private final MeteringRepository meteringRepository;

    @Inject
    StartMetering(SchedulerSwitcher<ContinuingMetering> schedulerSwitcher,
                  MeteringRepository meteringRepository) {
        super(schedulerSwitcher);
        this.meteringRepository = meteringRepository;
    }

    @Nonnull
    @Override
    protected Single<ContinuingMetering> source(MeterReadings readings) {
        return meteringRepository.startMetering(readings);
    }
}

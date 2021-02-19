package com.github.il4enkodev.househeating.domain.interactor.metering;

import com.github.il4enkodev.househeating.domain.entity.metering.CompletedMetering;
import com.github.il4enkodev.househeating.domain.interactor.SchedulerSwitcher;
import com.github.il4enkodev.househeating.domain.interactor.UseCaseSingle;
import com.github.il4enkodev.househeating.domain.repository.MeteringRepository;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Single;

public class EditMetering extends UseCaseSingle<CompletedMetering, EditMetering.Arguments> {

    private final MeteringRepository meteringRepository;

    @Inject
    EditMetering(SchedulerSwitcher<CompletedMetering> schedulerSwitcher,
                 MeteringRepository meteringRepository) {
        super(schedulerSwitcher);
        this.meteringRepository = meteringRepository;
    }

    @Nonnull
    @Override
    protected Single<CompletedMetering> source(Arguments arguments) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public static class Arguments {

    }

}

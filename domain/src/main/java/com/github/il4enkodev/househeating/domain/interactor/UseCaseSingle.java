package com.github.il4enkodev.househeating.domain.interactor;

import javax.annotation.Nonnull;

import io.reactivex.Single;

import static com.github.il4enkodev.househeating.domain.Preconditions.requireNotNull;

public abstract class UseCaseSingle<R, A> implements UseCase<Single<R>, A> {

    private final SchedulerSwitcher<R> schedulerSwitcher;

    protected UseCaseSingle(SchedulerSwitcher<R> schedulerSwitcher) {
        this.schedulerSwitcher = requireNotNull(schedulerSwitcher, "schedulerSwitcher");
    }

    @Nonnull
    protected abstract Single<R> source(A arguments);

    @Override
    public final Single<R> execute(A arguments) {
        return source(arguments).compose(schedulerSwitcher);
    }
}

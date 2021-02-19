package com.github.il4enkodev.househeating.domain.interactor;

import io.reactivex.Completable;

import static com.github.il4enkodev.househeating.domain.Preconditions.requireNotNull;

public abstract class UseCaseCompletable<A> implements UseCase<Completable, A> {

    private final SchedulerSwitcher<?> schedulerSwitcher;

    protected UseCaseCompletable(SchedulerSwitcher<?> schedulerSwitcher) {
        this.schedulerSwitcher = requireNotNull(schedulerSwitcher, "schedulerSwitcher");
    }

    protected abstract Completable source(A arguments);

    @Override
    public final Completable execute(A arguments) {
        return source(arguments).compose(schedulerSwitcher);
    }
}

package com.github.il4enkodev.househeating.domain.interactor;

import io.reactivex.Observable;

import static com.github.il4enkodev.househeating.domain.Preconditions.requireNotNull;

public abstract class UseCaseObservable<R, A> implements UseCase<Observable<R>, A> {

    private final SchedulerSwitcher<R> schedulerSwitcher;

    protected UseCaseObservable(SchedulerSwitcher<R> schedulerSwitcher) {
        this.schedulerSwitcher = requireNotNull(schedulerSwitcher, "schedulerSwitcher");
    }

    protected abstract Observable<R> source(A arguments);

    @Override
    public Observable<R> execute(A arguments) {
        return source(arguments).compose(schedulerSwitcher);
    }
}

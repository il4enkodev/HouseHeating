package com.github.il4enkodev.househeating.domain.interactor;

import com.github.il4enkodev.househeating.domain.di.ResultProcessingScheduler;
import com.github.il4enkodev.househeating.domain.di.WorkExecutionScheduler;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;

public final class SchedulerSwitcher<T> implements SingleTransformer<T, T>,
        MaybeTransformer<T, T>, ObservableTransformer<T, T>, CompletableTransformer {

    private final Scheduler workExecutor;
    private final Scheduler resultProcessor;

    @Inject
    public SchedulerSwitcher(@WorkExecutionScheduler Scheduler workExecutor,
                             @ResultProcessingScheduler Scheduler resultProcessor) {
        this.workExecutor = workExecutor;
        this.resultProcessor = resultProcessor;
    }

    @NonNull
    @Override
    public SingleSource<T> apply(@NonNull Single<T> upstream) {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor);
    }

    @NonNull
    @Override
    public MaybeSource<T> apply(@NonNull Maybe<T> upstream) {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor);
    }

    @NonNull
    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor);
    }

    @NonNull
    @Override
    public CompletableSource apply(@NonNull Completable upstream) {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor);
    }
}

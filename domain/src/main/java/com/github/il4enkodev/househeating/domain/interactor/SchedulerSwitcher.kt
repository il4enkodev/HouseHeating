package com.github.il4enkodev.househeating.domain.interactor

import com.github.il4enkodev.househeating.domain.di.ResultProcessingScheduler
import com.github.il4enkodev.househeating.domain.di.WorkExecutionScheduler
import io.reactivex.*
import javax.inject.Inject

class SchedulerSwitcher<T> @Inject constructor(
        @WorkExecutionScheduler private val workExecutor: Scheduler,
        @ResultProcessingScheduler private val resultProcessor: Scheduler
):  SingleTransformer<T, T>,
    MaybeTransformer<T, T>,
    ObservableTransformer<T, T>,
    CompletableTransformer {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor)
    }

    override fun apply(upstream: Maybe<T>): MaybeSource<T> {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor)
    }

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor)
    }

    override fun apply(upstream: Completable): CompletableSource {
        return upstream.subscribeOn(workExecutor).observeOn(resultProcessor)
    }
}
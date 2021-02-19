package com.github.il4enkodev.househeating.domain.interactor

import io.reactivex.Observable

abstract class UseCaseObservable<R, A> protected constructor(
        private val schedulerSwitcher: SchedulerSwitcher<R>) : UseCase<Observable<R>, A> {

    protected abstract fun source(arguments: A): Observable<R>

    override fun execute(arguments: A): Observable<R> {
        return source(arguments).compose(schedulerSwitcher)
    }
}
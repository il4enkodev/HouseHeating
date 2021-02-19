package com.github.il4enkodev.househeating.domain.interactor

import io.reactivex.Completable

abstract class UseCaseCompletable<A> protected constructor(
        private val schedulerSwitcher: SchedulerSwitcher<*>) : UseCase<Completable, A> {

    protected abstract fun source(arguments: A): Completable

    override fun execute(arguments: A): Completable {
        return source(arguments).compose(schedulerSwitcher)
    }
}
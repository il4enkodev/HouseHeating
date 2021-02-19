package com.github.il4enkodev.househeating.domain.interactor

import io.reactivex.Single

abstract class UseCaseSingle<R, A> protected constructor(
        private val schedulerSwitcher: SchedulerSwitcher<R>) : UseCase<Single<R>, A> {

    protected abstract fun source(arguments: A): Single<R>

    override fun execute(arguments: A): Single<R> {
        return source(arguments).compose(schedulerSwitcher)
    }
}
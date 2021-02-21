package com.github.il4enkodev.househeating.domain.entity

import com.github.il4enkodev.househeating.domain.exception.IllegalEntityStateException
import io.reactivex.functions.Function

@FunctionalInterface
interface EntityValidator<T : Entity<*>> : Function<T, T> {

    @Throws(IllegalEntityStateException::class)
    fun validate(entity: T)

    override fun apply(t: T): T {
        validate(t)
        return t
    }
}
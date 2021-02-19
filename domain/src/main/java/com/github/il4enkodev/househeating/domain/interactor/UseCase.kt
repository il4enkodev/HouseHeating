package com.github.il4enkodev.househeating.domain.interactor

interface UseCase<R, A> {
    fun execute(arguments: A): R
}
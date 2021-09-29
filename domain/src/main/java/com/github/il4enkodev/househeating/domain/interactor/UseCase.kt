package com.github.il4enkodev.househeating.domain.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

import com.github.il4enkodev.househeating.domain.util.Result

abstract class UseCase<in A, R>(
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(arguments: A): Result<R> {
        return try {
            withContext(dispatcher) {
                execute(arguments).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    protected abstract suspend fun execute(arguments: A): R
}
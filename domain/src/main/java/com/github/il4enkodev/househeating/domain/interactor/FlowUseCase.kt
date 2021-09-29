package com.github.il4enkodev.househeating.domain.interactor

import com.github.il4enkodev.househeating.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
abstract class FlowUseCase<in A, R>(private val dispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(arguments: A): Flow<Result<R>> = execute(arguments)
        .map<R, Result<R>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { e -> emit(Result.Error(Exception(e))) }
        .flowOn(dispatcher)

    protected abstract suspend fun execute(arguments: A): Flow<R>
}
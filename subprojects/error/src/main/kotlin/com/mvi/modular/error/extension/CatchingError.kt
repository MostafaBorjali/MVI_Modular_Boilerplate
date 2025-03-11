package com.mvi.modular.error.extension

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.service.ErrorService


/**
 * Run the specified function [block] and returns its encapsulated result if invocation was successful,
 * catching any [Throwable] exception that was thrown from the [block] function execution and converting it to a [Error].
 */
inline fun <T> ErrorService.launchApiCatchingError(block: () -> T): Either<T, Error> {
    return try {
        Either.Success(block())
    } catch (exception: Exception) {
        Either.Error(this.convert(exception))
    }
}
package com.mvi.modular.base.functional


sealed class Either<out T, out E> {

    /**
     *
     */
    data class Success<T, E>(val data: T) : Either<T, E>()

    /**
     *
     */
    data class Error<T, E>(val error: E) : Either<T, E>()

    /**
     * Check this is a [Success] reference or not
     */
    fun ok(): Boolean {
        return this is Success
    }
}

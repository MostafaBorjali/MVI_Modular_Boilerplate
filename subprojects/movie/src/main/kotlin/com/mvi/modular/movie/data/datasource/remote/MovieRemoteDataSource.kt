package com.mvi.modular.movie.data.datasource.remote

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.movie.data.datasource.remote.entity.MovieEntity


internal interface MovieRemoteDataSource {

    /**
     * Get home data
     */
    suspend fun getListOfMovies(pageNumber: Int, lang: String): Either<List<MovieEntity>?, Error>

}
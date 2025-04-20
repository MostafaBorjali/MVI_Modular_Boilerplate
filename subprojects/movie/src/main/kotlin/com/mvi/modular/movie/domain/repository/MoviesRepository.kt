package com.mvi.modular.movie.domain.repository

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.movie.domain.model.MovieDto


internal interface MoviesRepository {


    /**
     * Get list of available movies
     */
    suspend fun getListOfMovies(pageNumber: Int): Either<List<MovieDto>?, Error>

}
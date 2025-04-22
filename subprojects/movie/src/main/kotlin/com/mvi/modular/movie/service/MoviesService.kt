package com.mvi.modular.movie.service


import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.movie.domain.model.MovieDto


interface MoviesService {

    /**
     * Get movies list
     *
     * @param pageNumber use this flag to get data from remote by pagination
     */
    suspend fun getListOfMovies(
        pageNumber: Int,
        lang:String,
        ): Either<List<MovieDto>?, Error>

}
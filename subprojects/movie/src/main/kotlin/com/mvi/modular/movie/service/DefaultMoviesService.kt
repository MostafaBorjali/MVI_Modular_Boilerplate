package com.mvi.modular.movie.service


import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.movie.domain.model.MovieDto
import com.mvi.modular.movie.domain.usecase.GetListOfMoviesUseCase


internal class DefaultMoviesService(
    private val getListOfMoviesUseCase: GetListOfMoviesUseCase,
) : MoviesService {


    override suspend fun getListOfMovies(
        pageNumber: Int,
        lang: String
    ): Either<List<MovieDto>?, Error> {
        return getListOfMoviesUseCase(pageNumber,lang)
    }

}
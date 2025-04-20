package com.mvi.modular.movie.domain.usecase

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.movie.domain.model.MovieDto
import com.mvi.modular.movie.domain.repository.MoviesRepository


internal interface GetListOfMoviesUseCase {
    /**
     *
     */
    suspend operator fun invoke(pageNumber: Int): Either<List<MovieDto>?, Error>
}


internal class DefaultGetListOfMoviesUseCase(
    private val moviesRepository: MoviesRepository,
) : GetListOfMoviesUseCase {

    override suspend fun invoke(pageNumber: Int): Either<List<MovieDto>?, Error> {
        return moviesRepository.getListOfMovies(pageNumber)
    }
}
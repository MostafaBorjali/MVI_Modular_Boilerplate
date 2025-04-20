package com.mvi.modular.movie.data.repository

import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.movie.data.datasource.remote.MovieRemoteDataSource
import com.mvi.modular.movie.data.mapper.toMovies
import com.mvi.modular.movie.domain.model.MovieDto
import com.mvi.modular.movie.domain.repository.MoviesRepository


internal class DefaultMoviesRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MoviesRepository {

    override suspend fun getListOfMovies(pageNumber: Int): Either<List<MovieDto>?, Error> {
        return when (val response = movieRemoteDataSource.getListOfMovies(pageNumber)) {
            is Either.Success -> {
                Either.Success(response.data?.toMovies())
            }

            is Either.Error -> {
                Either.Error(response.error)
            }
        }
    }
}
package com.mvi.modular.movie.di

import com.mvi.modular.movie.data.datasource.remote.DefaultMovieRemoteDataSource
import com.mvi.modular.movie.data.datasource.remote.MovieApi
import com.mvi.modular.movie.data.datasource.remote.MovieRemoteDataSource
import com.mvi.modular.movie.data.repository.DefaultMoviesRepository
import com.mvi.modular.movie.domain.repository.MoviesRepository
import com.mvi.modular.movie.domain.usecase.DefaultGetListOfMoviesUseCase
import com.mvi.modular.movie.service.DefaultMoviesService
import com.mvi.modular.movie.service.MoviesService
import org.koin.dsl.module
import retrofit2.Retrofit


val movieModule = module {

    single<MovieRemoteDataSource> {
        fun provideMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }

        DefaultMovieRemoteDataSource(
            movieApi = provideMovieApi(get()),
            errorService = get(),
        )
    }

    single<MoviesRepository> {
        DefaultMoviesRepository(
            movieRemoteDataSource = get(),
        )
    }

    single<MoviesService> {
        val getListOfMoviesUseCase = DefaultGetListOfMoviesUseCase(
            moviesRepository = get(),
        )

        DefaultMoviesService(
            getListOfMoviesUseCase = getListOfMoviesUseCase,
        )
    }
}
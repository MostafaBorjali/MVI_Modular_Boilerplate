package com.ammotel.android.esim.data.repository

import com.google.common.truth.Truth.assertThat
import com.mvi.modular.movie.data.datasource.remote.MovieRemoteDataSource
import com.mvi.modular.movie.data.datasource.remote.entity.MovieEntity
import com.mvi.modular.movie.data.repository.DefaultMoviesRepository
import com.mvi.modular.movie.domain.repository.MoviesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import com.mvi.modular.base.functional.Either
import com.mvi.modular.movie.domain.model.MovieDto


class MoviesRepositoryTest {

    private lateinit var moviesRepository: MoviesRepository
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Before
    fun setUp() {
        movieRemoteDataSource = Mockito.mock(MovieRemoteDataSource::class.java)
        moviesRepository = DefaultMoviesRepository(movieRemoteDataSource)
    }


    @Test
    fun `test Top Movies get from remote if cache is empty`() = runBlocking {
        val response = listOf(
            MovieEntity(
                title = "1",
                overview = "w220_and_h330_face",
                posterPath = "https://media.themoviedb.org/t/p/w220_and_h330_face/9rCBCm9cyI4JfLEhx3EncyncMR3.jpg",
            ),
            MovieEntity(
                title = "2",
                overview = "w220_and_h330_face",
                posterPath = "https://media.themoviedb.org/t/p/w220_and_h330_face/9rCBCm9cyI4JfLEhx3EncyncMR3.jpg",
            ),
            MovieEntity(
                title = "3",
                overview = "w220_and_h330_face",
                posterPath = "https://media.themoviedb.org/t/p/w220_and_h330_face/9rCBCm9cyI4JfLEhx3EncyncMR3.jpg",
            ),
        )

        `when`(movieRemoteDataSource.getListOfMovies(1)).thenReturn(Either.Success(response))

        val result = moviesRepository.getListOfMovies(1)
        verify(movieRemoteDataSource, times(1)).getListOfMovies(1)
        assertThat(result).isInstanceOf(Either.Success::class.java)
    }

}
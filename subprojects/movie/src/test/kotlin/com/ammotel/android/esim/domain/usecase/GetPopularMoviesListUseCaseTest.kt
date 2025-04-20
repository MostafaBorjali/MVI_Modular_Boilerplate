package com.ammotel.android.esim.domain.usecase


import com.google.common.truth.Truth.assertThat
import com.mvi.modular.base.functional.Either
import com.mvi.modular.movie.domain.model.MovieDto
import com.mvi.modular.movie.domain.repository.MoviesRepository
import com.mvi.modular.movie.domain.usecase.DefaultGetListOfMoviesUseCase
import com.mvi.modular.movie.domain.usecase.GetListOfMoviesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class GetPopularMoviesListUseCaseTest {

    private lateinit var getListOfMoviesUseCase: GetListOfMoviesUseCase
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        moviesRepository = Mockito.mock(MoviesRepository::class.java)
        getListOfMoviesUseCase = DefaultGetListOfMoviesUseCase(moviesRepository)
    }


    @Test
    fun `test get popular movies must return popular movies and sorted by name`() =
        runBlocking {
            val movies = listOf(
                MovieDto(
                    title = "1",
                    overview = "w220_and_h330_face",
                    posterPath = "https://media.themoviedb.org/t/p/w220_and_h330_face/9rCBCm9cyI4JfLEhx3EncyncMR3.jpg",
                ),
                MovieDto(
                    title = "2",
                    overview = "w220_and_h330_face",
                    posterPath = "https://media.themoviedb.org/t/p/w220_and_h330_face/9rCBCm9cyI4JfLEhx3EncyncMR3.jpg",
                ),
            )
            Mockito.`when`(moviesRepository.getListOfMovies(1))
                .thenReturn(Either.Success(movies))

            val result = getListOfMoviesUseCase(1)
            assertThat(result).isInstanceOf(Either.Success::class.java)
            val list = (result as Either.Success).data
            assertThat(list?.size).isEqualTo(2)
            assertThat(list?.get(0)).isEqualTo(movies[0])
            assertThat(list?.get(1)).isEqualTo(movies[1])
        }
}
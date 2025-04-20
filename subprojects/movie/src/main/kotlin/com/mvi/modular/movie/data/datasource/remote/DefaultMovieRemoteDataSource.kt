package com.mvi.modular.movie.data.datasource.remote


import android.annotation.SuppressLint
import com.mvi.modular.base.functional.Either
import com.mvi.modular.error.domain.model.Error
import com.mvi.modular.error.extension.launchApiCatchingError
import com.mvi.modular.error.service.ErrorService
import com.mvi.modular.movie.data.datasource.remote.entity.MovieEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


internal class DefaultMovieRemoteDataSource(
    private val movieApi: MovieApi,
    private val errorService: ErrorService,
) : MovieRemoteDataSource {


    override suspend fun getListOfMovies(pageNumber: Int): Either<List<MovieEntity>?, Error> {
        return errorService.launchApiCatchingError {
            movieApi.getListOfMovies(
                page = pageNumber,
                fromDate = getLastMonthDate(),
                toDate = getCurrentDate()
            ).results
        }
    }

    /**
     * Gets the current date formatted as a string in YYYY-MM-DD format.
     */
    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(Date())
    }

    /**
     * Gets the last month date formatted as a string in YYYY-MM-DD format.
     */
    @SuppressLint("SimpleDateFormat")
    private fun getLastMonthDate(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, -1) // Subtract 1 month
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(cal.time)
    }
}
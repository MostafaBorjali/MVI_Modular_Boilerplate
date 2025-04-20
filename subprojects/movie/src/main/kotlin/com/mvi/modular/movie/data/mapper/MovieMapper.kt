package com.mvi.modular.movie.data.mapper

import com.mvi.modular.movie.data.datasource.remote.entity.MovieEntity
import com.mvi.modular.movie.domain.model.MovieDto


//
// movie
//

internal fun List<MovieEntity>.toMovies(): List<MovieDto> {
    return this.map { it.movieDto() }
}

internal fun MovieEntity.movieDto(): MovieDto {
    return MovieDto(title = title, overview = overview, posterPath = posterPath)
}


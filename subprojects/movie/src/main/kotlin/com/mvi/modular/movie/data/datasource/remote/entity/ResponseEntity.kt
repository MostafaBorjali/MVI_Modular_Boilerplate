package com.mvi.modular.movie.data.datasource.remote.entity

import com.google.gson.annotations.SerializedName

//
// Base server response
//
internal class ServerResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: T? = null,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)

internal data class MovieEntity(
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,

    )
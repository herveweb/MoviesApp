package com.example.moviesapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@JsonClass(generateAdapter = true)
data class ApiMovieListResult(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Movie>
)
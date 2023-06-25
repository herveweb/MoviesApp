package com.example.moviesapp.models.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RemoteMovieList(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<RemoteMovie>
)
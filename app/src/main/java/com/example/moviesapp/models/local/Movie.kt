package com.example.moviesapp.models.local

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "title") val title: String? = "",
    @Json(name = "poster_path") val image: String? = "",
    @Json(name = "overview") val description: String? = "",
    @Json(name = "backdrop_path") val backDropImage: String? = "",
    @Json(name = "revenue") val revenue: Long? = 0L,
    @Json(name = "release_date") val releaseDate: String? = ""
)
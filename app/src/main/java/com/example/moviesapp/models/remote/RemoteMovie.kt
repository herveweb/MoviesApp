package com.example.moviesapp.models.remote

import com.example.moviesapp.models.local.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RemoteMovie(
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "title") val title: String? = "",
    @Json(name = "poster_path") val image: String? = "",
    @Json(name = "overview") val description: String? = "",
    @Json(name = "backdrop_path") val backDropImage: String? = "",
    @Json(name = "revenue") val revenue: Long? = 0L,
    @Json(name = "release_date") val releaseDate: String? = ""
)

fun RemoteMovie.toLocalMovie() = Movie(
    id = id,
    title = title,
    image = image,
    description = description,
    backDropImage = backDropImage,
    revenue = revenue,
    releaseDate = releaseDate,
)
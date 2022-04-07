package com.example.moviesapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id") val id: Int?,
    @Json(name = "title") val title: String?,
    @Json(name = "poster_path") val image: String?,
    @Json(name = "overview") val description: String?,
    @Json(name = "backdrop_path") val backDropImage: String?,
    @Json(name = "revenue") val revenue: Long? = 0,
    @Json(name = "release_date") val releaseDate: String? = ""
) {
    constructor() : this(0, "", "", "", "", 0L, "")
}
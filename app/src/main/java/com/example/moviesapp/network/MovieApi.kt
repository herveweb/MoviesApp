package com.example.moviesapp.network

import ApiResult
import com.example.moviesapp.models.Movie

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
interface MovieApi {
    suspend fun getMovies(page: Int): ApiResult<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): ApiResult<Movie?>
}

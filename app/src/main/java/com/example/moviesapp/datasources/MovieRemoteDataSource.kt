package com.example.moviesapp.datasources

import ApiResult
import com.example.moviesapp.models.Movie
import com.example.moviesapp.network.MovieApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
class MovieRemoteDataSource(
    private val movieApi: MovieApi,
) {
    suspend fun getMoviesList(page: Int): ApiResult<List<Movie>> =
        movieApi.getMovies(page)

    suspend fun getMovieDetails(movieId: Int): ApiResult<Movie?> =
        movieApi.getMovieDetails(movieId)
}
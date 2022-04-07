package com.example.moviesapp.datasources

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
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getMoviesList(page: Int): List<Movie> =
        withContext(ioDispatcher) {
            movieApi.getMovies(page)
        }

    suspend fun getMovieDetails(movieId: Int): Movie =
        withContext(ioDispatcher) {
            movieApi.getMovieDetails(movieId)
        }
}
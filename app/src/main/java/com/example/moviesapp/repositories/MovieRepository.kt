package com.example.moviesapp.repositories

import ApiResult
import android.R
import android.app.AlertDialog
import com.example.moviesapp.datasources.MovieRemoteDataSource
import com.example.moviesapp.models.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) {

    suspend fun getMovies(page: Int): ApiResult<List<Movie>> =
        movieRemoteDataSource.getMoviesList(page)

    suspend fun getMovieDetails(movieId: Int): ApiResult<Movie?> =
        movieRemoteDataSource.getMovieDetails(movieId)
}

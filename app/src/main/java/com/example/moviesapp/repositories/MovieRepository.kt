package com.example.moviesapp.repositories

import com.example.moviesapp.datasources.MovieRemoteDataSource
import com.example.moviesapp.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val scope: CoroutineScope,
) {
    private val moviesMutex = Mutex()
    private var movies: List<Movie> = emptyList()
    private lateinit var movieDetails: Movie

    suspend fun getMovies(page: Int): List<Movie> {
        return withContext(scope.coroutineContext) {
            movieRemoteDataSource.getMoviesList(page).also { networkResult ->
                moviesMutex.withLock {
                    movies = networkResult
                }
            }
        }
    }

    suspend fun getMovieDetails(movieId: Int): Movie{
        return withContext(scope.coroutineContext) {
            movieRemoteDataSource.getMovieDetails(movieId).also { networkResult ->
                moviesMutex.withLock {
                    movieDetails = networkResult
                }
            }
        }
    }
}

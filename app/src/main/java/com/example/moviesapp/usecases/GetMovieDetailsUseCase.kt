package com.example.moviesapp.usecases

import com.example.moviesapp.models.Movie
import com.example.moviesapp.repositories.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(movieId: Int): Movie =
        withContext(defaultDispatcher) {
            movieRepository.getMovieDetails(movieId)
        }
}

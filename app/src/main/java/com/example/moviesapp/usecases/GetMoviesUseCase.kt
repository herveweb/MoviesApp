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
class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(page: Int): List<Movie> =
        withContext(defaultDispatcher) {
            movieRepository.getMovies(page)
        }
}

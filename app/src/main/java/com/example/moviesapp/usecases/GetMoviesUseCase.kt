package com.example.moviesapp.usecases

import ApiResult
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
) {
    suspend operator fun invoke(page: Int): ApiResult<List<Movie>> =
        movieRepository.getMovies(page)
}

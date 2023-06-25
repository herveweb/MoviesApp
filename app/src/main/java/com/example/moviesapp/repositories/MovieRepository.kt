package com.example.moviesapp.repositories

import com.example.moviesapp.datasources.MovieRemoteDataSource
import com.example.moviesapp.models.local.Movie
import com.example.moviesapp.models.remote.toLocalMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf


class MovieRepository(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) {
    suspend fun getMovies(page: Int): Flow<List<Movie>?> = flowOf(
        movieRemoteDataSource.getMoviesList(page).body()?.results?.map { movie ->
            Movie(
                id = movie.id,
                title = movie.title,
                image = "https://image.tmdb.org/t/p/w185${movie.image}",
                description = movie.description,
                backDropImage = movie.backDropImage,
                revenue = movie.revenue,
                releaseDate = movie.releaseDate
            )
        }
    )

    suspend fun getMovieDetails(movieId: String): Flow<Movie?> =
        flowOf(movieRemoteDataSource.getMovieDetails(movieId).body()?.toLocalMovie())
}

package com.example.moviesapp.network

import com.example.moviesapp.models.Movie

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
class MovieApiImpl constructor(
    private val moviesNetworkService: NetworkService
) : MovieApi {
    override suspend fun getMovies(page: Int): List<Movie> {
        val apiMovieListResult = moviesNetworkService.getMovies(page)
        val result = apiMovieListResult.results
        val movies = arrayListOf<Movie>()
        for (movie in result) {
            movies.add(
                Movie(
                    id = movie.id,
                    title = movie.title,
                    image = movie.image,
                    description = movie.description,
                    backDropImage = movie.backDropImage,
                    revenue = movie.revenue,
                    releaseDate = movie.releaseDate
                )
            )
        }
        return movies
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        return moviesNetworkService.getMovieDetails(movieId)
    }
}
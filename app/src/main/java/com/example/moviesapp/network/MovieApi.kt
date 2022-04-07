package com.example.moviesapp.network

import com.example.moviesapp.models.Movie

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
interface MovieApi {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovieDetails(movieId: Int): Movie
}

package com.example.moviesapp.uistates

import com.example.moviesapp.models.Movie

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
data class MovieListUiState(
    val movies: List<Movie> = listOf(),
    val page: Int = 1,
    val isLoadingMovies: Boolean = false,
)
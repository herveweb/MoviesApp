package com.example.moviesapp.uistates

import com.example.moviesapp.models.Movie

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
data class MovieDetailsUiState(
    val movie: Movie = Movie(),
    val isLoadingMovie: Boolean = false,
)
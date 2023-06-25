package com.example.moviesapp.screens.moviedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.local.Movie
import com.example.moviesapp.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState: StateFlow<MovieDetailsUiState> = _uiState.asStateFlow()

    private val movieId: String = checkNotNull(savedStateHandle["movieId"])

    init {
        viewModelScope.launch {
            getMovieDetails(movieId = movieId)
        }
    }

    private suspend fun getMovieDetails(movieId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMovie = true) }
            movieRepository.getMovieDetails(movieId).collect{ movie ->
                movie?.let {
                    _uiState.update {
                        it.copy(isLoadingMovie = false, movie = movie)
                    }
                }
            }
        }
    }
}
data class MovieDetailsUiState(
    val movie: Movie = Movie(),
    val isLoadingMovie: Boolean = false,
    val errorMessage: String? = ""
)
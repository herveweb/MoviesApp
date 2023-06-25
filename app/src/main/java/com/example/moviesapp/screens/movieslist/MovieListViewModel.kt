package com.example.moviesapp.screens.movieslist

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
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
    ) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        _uiState.update { it.copy(isLoadingMovies = true) }
        movieRepository.getMovies(_uiState.value.page).collect{ movies ->
            movies?.let {
                _uiState.update {
                    it.copy(isLoadingMovies = false, movieList = movies, page = it.page + 1)
                }
            }
        }
    }

    fun onListItemClick(movieId: String) {
        _uiState.update { it.copy(navigateTo = NavigationDestination.MovieDetails(movieId)) }
    }

    fun resetNavigation() {
        _uiState.update { it.copy(
            navigateTo = NavigationDestination.None,
        ) }
    }

    fun clearErrorMessages(){
        _uiState.update { it.copy(errorMessage = "") }
    }
}
data class MovieListUiState(
    val movieList: List<Movie> = listOf(),
    val page: Int = 1,
    val isLoadingMovies: Boolean = false,
    val errorMessage: String? = "",
    val navigateTo: NavigationDestination = NavigationDestination.None,
)

sealed class NavigationDestination {
    data class MovieDetails(val movieId: String): NavigationDestination()
    object None: NavigationDestination()
}
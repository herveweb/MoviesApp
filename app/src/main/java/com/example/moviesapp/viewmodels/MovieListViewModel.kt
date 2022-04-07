package com.example.moviesapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.uistates.MovieListUiState
import com.example.moviesapp.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieListUiState())
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    suspend fun getMovies() {
        _uiState.update {
            it.copy(isLoadingMovies = true)
        }
        val movies = getMoviesUseCase(_uiState.value.page)
        if (!movies.isNullOrEmpty()) {
            _uiState.update {
                it.copy(isLoadingMovies = false, movies = movies, page = it.page + 1)
            }
        }
    }
}
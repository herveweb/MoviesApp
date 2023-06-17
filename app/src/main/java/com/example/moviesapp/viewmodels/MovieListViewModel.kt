package com.example.moviesapp.viewmodels

import ApiResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.Movie
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
        _uiState.update { it.copy(isLoadingMovies = true) }
        when(val result: ApiResult<List<Movie>> = getMoviesUseCase(_uiState.value.page)){
            is ApiResult.Success -> updateMovies(result.data)
            is ApiResult.Failure -> _uiState.update { it.copy(errorMessage = result.errorMessage) }
            else -> {}
        }
    }

    private fun updateMovies(movies: List<Movie>){
        if (!movies. isNullOrEmpty()) {
            _uiState.update {
                it.copy(isLoadingMovies = false, movies = movies, page = it.page + 1)
            }
        }
    }

    fun clearErrorMessages(){
        _uiState.update { it.copy(errorMessage = "") }
    }
}
data class MovieListUiState(
    val movies: List<Movie> = listOf(),
    val page: Int = 1,
    val isLoadingMovies: Boolean = false,
    val errorMessage: String? = ""
)
package com.example.moviesapp.viewmodels

import ApiResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.models.Movie
import com.example.moviesapp.usecases.GetMovieDetailsUseCase
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
class MovieDetailsViewModel @Inject constructor(val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState: StateFlow<MovieDetailsUiState> = _uiState.asStateFlow()

    suspend fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMovie = true) }
            when(val result = getMovieDetailsUseCase(movieId)){
                is ApiResult.Success -> {
                    result.data?.let {movie ->
                        _uiState.update {
                            it.copy(isLoadingMovie = false, movie = movie)
                        }
                    }
                }
                is ApiResult.Failure -> _uiState.update { it.copy(errorMessage = result.errorMessage) }
                else -> {}
            }
        }
    }
}
data class MovieDetailsUiState(
    val movie: Movie = Movie(),
    val isLoadingMovie: Boolean = false,
    val errorMessage: String? = ""
)
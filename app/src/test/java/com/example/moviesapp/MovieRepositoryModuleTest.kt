package com.example.moviesapp

import ApiResult
import com.example.moviesapp.di.MovieRepositoryModule
import com.example.moviesapp.models.Movie
import com.example.moviesapp.repositories.MovieRepository
import com.example.moviesapp.usecases.GetMoviesUseCase
import com.example.moviesapp.viewmodels.MovieListViewModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test





class MovieRepositoryModuleTest {
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun test1(){
        //Given
        val module = mockk<MovieRepositoryModule>()

        //When
        module.provideMovieRepository(mockk(), mockk())

        //Then
        module.shouldBeInstanceOf<MovieRepository>()
    }

    @Test
    fun test2() = runBlocking {
        //Given
        val getMoviesUseCase = mockk<GetMoviesUseCase>()
        val moviesList = listOf(Movie())
        val apiResult = ApiResult.Success(moviesList)
        coEvery { getMoviesUseCase.invoke(any()) } returns apiResult
        var movieListViewModel = MovieListViewModel(getMoviesUseCase)

        //When
            movieListViewModel.uiState.collect { movieListState ->
                movieListState.movies.size.shouldBe(1)
                movieListState.movies[0].shouldBeInstanceOf<Movie>()
                //movieListState.movies.shouldBe(moviesList)
            }
            movieListViewModel.getMovies()
    }
}

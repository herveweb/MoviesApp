package com.example.moviesapp.api.movieapi

import com.example.moviesapp.models.remote.RemoteMovieList
import com.example.moviesapp.models.remote.RemoteMovie
import com.example.moviesapp.api.NetworkService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    @GET("movie/popular?api_key=${NetworkService.apiKey}")
    suspend fun getMovies(@Query("page") page: Int): Response<RemoteMovieList>

    @GET("movie/{movieId}?api_key=${NetworkService.apiKey}&language=en-US")
    suspend fun getMovieDetails(@Path("movieId") movieId: String): Response<RemoteMovie>
}

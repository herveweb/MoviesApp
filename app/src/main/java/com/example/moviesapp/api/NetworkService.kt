package com.example.moviesapp.api

import com.example.moviesapp.models.remote.RemoteMovieList
import com.example.moviesapp.models.remote.RemoteMovie
import com.example.moviesapp.api.movieapi.MovieApi
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkService {
    companion object {
        const val apiKey = "ffc2f84565d803102c5a81eda2bce966"
        const val baseUrl = "https://api.themoviedb.org/3/"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val movieApi = retrofit.create(MovieApi::class.java)

    suspend fun getMovies(page: Int): Response<RemoteMovieList> = movieApi.getMovies(page)

    suspend fun getMovieDetails(movieId: String): Response<RemoteMovie> = movieApi.getMovieDetails(movieId)
}
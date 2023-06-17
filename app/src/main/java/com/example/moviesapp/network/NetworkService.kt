package com.example.moviesapp.network

import ApiResult
import com.example.moviesapp.models.ApiMovieListResult
import com.example.moviesapp.models.Movie
import com.example.moviesapp.network.NetworkService.Companion.apiKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    private val moviesService = retrofit.create(MoviesService::class.java)

    suspend fun getMovies(page: Int): Response<ApiMovieListResult> = moviesService.getMovies(page)

    suspend fun getMovieDetails(movieId: Int): Response<Movie> = moviesService.getMovieDetails(movieId)
}

interface MoviesService {
    @GET("movie/popular?api_key=$apiKey")
    suspend fun getMovies(@Query("page") page: Int): Response<ApiMovieListResult>

    @GET("movie/{movieId}?api_key=$apiKey&language=en-US")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): Response<Movie>
}
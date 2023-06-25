package com.example.moviesapp.datasources

import com.example.moviesapp.apis.NetworkService
import com.example.moviesapp.models.remote.RemoteMovieList
import com.example.moviesapp.models.remote.RemoteMovie
import retrofit2.Response


class MovieRemoteDataSource(
    private val networkService: NetworkService,
) {
    suspend fun getMoviesList(page: Int): Response<RemoteMovieList> =
        networkService.getMovies(page)

    suspend fun getMovieDetails(movieId: String): Response<RemoteMovie> =
        networkService.getMovieDetails(movieId)
}
package com.example.moviesapp.network

import ApiResult
import com.example.moviesapp.models.ApiMovieListResult
import com.example.moviesapp.models.Movie
import retrofit2.Response
import java.lang.Exception

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
class MovieApiImpl constructor(
    private val moviesNetworkService: NetworkService
) : MovieApi {
    override suspend fun getMovies(page: Int): ApiResult<List<Movie>>  {
        val response = moviesNetworkService.getMovies(page)
        return if(response.isSuccessful) {
            val movies = arrayListOf<Movie>()
            val apiMovieListResult = response.body()
            apiMovieListResult?.results?.let {
                for (movie in it) {
                    movies.add(
                        Movie(
                            id = movie.id,
                            title = movie.title,
                            image = movie.image,
                            description = movie.description,
                            backDropImage = movie.backDropImage,
                            revenue = movie.revenue,
                            releaseDate = movie.releaseDate
                        )
                    )
                }
            }
            ApiResult.Success(movies)
        } else {
            ApiResult.Failure(response.message())
        }
    }

    override suspend fun getMovieDetails(movieId: Int): ApiResult<Movie?> {
        val response = moviesNetworkService.getMovieDetails(movieId)
        return if(response.isSuccessful) {
            ApiResult.Success(response?.body())
        } else {
            ApiResult.Failure(response.message())
        }
    }
}
package com.example.moviesapp.di

import com.example.moviesapp.datasources.MovieRemoteDataSource
import com.example.moviesapp.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@Module
@InstallIn(SingletonComponent::class)
class MovieRemoteDataSourceModule {

    @Singleton
    @Provides
    internal fun provideMovieRemoteDataSource(
        movieApi: MovieApi,
    ): MovieRemoteDataSource = MovieRemoteDataSource(movieApi)

}
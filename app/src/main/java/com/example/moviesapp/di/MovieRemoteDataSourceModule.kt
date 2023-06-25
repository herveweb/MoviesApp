package com.example.moviesapp.di

import com.example.moviesapp.apis.NetworkService
import com.example.moviesapp.datasources.MovieRemoteDataSource
import com.example.moviesapp.apis.movieapi.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MovieRemoteDataSourceModule {

    @Singleton
    @Provides
    internal fun provideMovieRemoteDataSource(
        networkService: NetworkService,
    ): MovieRemoteDataSource = MovieRemoteDataSource(networkService)

}
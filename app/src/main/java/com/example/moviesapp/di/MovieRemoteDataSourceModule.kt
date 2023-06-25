package com.example.moviesapp.di

import com.example.moviesapp.api.NetworkService
import com.example.moviesapp.datasources.MovieRemoteDataSource
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
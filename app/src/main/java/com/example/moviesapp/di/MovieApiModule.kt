package com.example.moviesapp.di

import com.example.moviesapp.network.MovieApi
import com.example.moviesapp.network.MovieApiImpl
import com.example.moviesapp.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@Module
@InstallIn(SingletonComponent::class)
class MovieApiModule {

    @Singleton
    @Provides
    internal fun provideMovieApi(moviesNetworkService: NetworkService): MovieApi = MovieApiImpl(moviesNetworkService)
}
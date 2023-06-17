package com.example.moviesapp.di

import com.example.moviesapp.datasources.MovieRemoteDataSource
import com.example.moviesapp.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@Module
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {

    @Singleton
    @Provides
    internal fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
    ): MovieRepository = MovieRepository(movieRemoteDataSource)

}
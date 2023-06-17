package com.example.moviesapp.di

import com.example.moviesapp.repositories.MovieRepository
import com.example.moviesapp.usecases.GetMovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@Module
@InstallIn(SingletonComponent::class)
class GetMovieDetailsUseCaseModule {

    @Singleton
    @Provides
    internal fun provideGetMovieDetailsUseCase(
        movieRepository: MovieRepository
    ): GetMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)

}
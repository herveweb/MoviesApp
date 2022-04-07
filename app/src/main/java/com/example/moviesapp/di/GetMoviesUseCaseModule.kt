package com.example.moviesapp.di

import com.example.moviesapp.repositories.MovieRepository
import com.example.moviesapp.usecases.GetMoviesUseCase
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
class GetMoviesUseCaseModule {

    @Singleton
    @Provides
    internal fun provideGetMoviesUseCase(
        movieRepository: MovieRepository,
        dispatcher: CoroutineDispatcher
    ): GetMoviesUseCase = GetMoviesUseCase(movieRepository, dispatcher)

}
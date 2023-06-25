package com.example.moviesapp.di

import com.example.moviesapp.apis.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkServiceModule {

    @Singleton
    @Provides
    internal fun provideNetworkService(): NetworkService = NetworkService()
}
package com.example.data.di

import com.example.data.api.ApiService
import com.example.data.reposiory.MarvelDetailRepositoryImpl
import com.example.data.reposiory.MarvelRepositoryImpl
import com.example.domain.repository.MarvelDetailRepository
import com.example.domain.repository.MarvelListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMarveListRepository(
        apiService: ApiService
    ): MarvelListRepository {
        return MarvelRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideMarvelDetailRepository(
        apiService: ApiService
    ): MarvelDetailRepository {
        return MarvelDetailRepositoryImpl(apiService)
    }
}

package com.example.marvel.di

import com.example.data.reposiory.MarvelDetailRepositoryImpl
import com.example.data.reposiory.MarvelRepositoryImpl
import com.example.domain.repository.MarvelDetailRepository
import com.example.domain.repository.MarvelListRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MarvelListRepository> { MarvelRepositoryImpl(get()) }

    single<MarvelDetailRepository> { MarvelDetailRepositoryImpl(get()) }
}

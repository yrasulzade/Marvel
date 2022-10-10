package com.example.marvel.di

import com.example.domain.usecase.MarvelDetailsUseCase
import com.example.domain.usecase.MarvelListUseCase
import org.koin.dsl.module

val useCaseModules = module {
    single { MarvelListUseCase(get()) }

    single { MarvelDetailsUseCase(get()) }
}

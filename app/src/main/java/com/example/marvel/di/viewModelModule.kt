package com.example.marvel.di

import com.example.details.DetailsViewModel
import com.example.marvel.ui.MainViewModel
import com.example.marvel_list.ui.MarvelListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel() }

    viewModel { MarvelListViewModel(get()) }

    viewModel { DetailsViewModel(get()) }

}

package com.example.marvel

import android.app.Application
import com.example.marvel.di.networkModule
import com.example.marvel.di.repositoryModule
import com.example.marvel.di.useCaseModules
import com.example.marvel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(viewModelModule)

            modules(networkModule)
            modules(repositoryModule)
            modules(useCaseModules)
        }
    }
}

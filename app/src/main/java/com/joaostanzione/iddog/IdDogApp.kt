package com.joaostanzione.iddog

import android.app.Application
import com.joaostanzione.iddog.infrastructure.di.apiModule
import com.joaostanzione.iddog.infrastructure.di.domainModule
import com.joaostanzione.iddog.infrastructure.di.repositoryModule
import com.joaostanzione.iddog.infrastructure.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IdDogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@IdDogApp)
            modules(listOf(repositoryModule, domainModule, viewModelModule, apiModule))
        }
    }
}

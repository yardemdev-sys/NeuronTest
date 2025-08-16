package com.example.neurontest.app

import android.app.Application
import com.example.neurontest.app.di.localStorageModule
import com.example.neurontest.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NeuronTestApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NeuronTestApp)
            modules(localStorageModule, viewModelModule)
        }
    }
}
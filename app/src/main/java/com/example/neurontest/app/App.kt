package com.example.neurontest.app

import android.app.Application
import com.example.neurontest.app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NeuronTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NeuronTestApp)
            modules(appModules)
        }
    }
}

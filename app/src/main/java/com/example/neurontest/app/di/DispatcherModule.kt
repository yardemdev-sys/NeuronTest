package com.example.neurontest.app.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher> {
        Dispatchers.IO
    }
}
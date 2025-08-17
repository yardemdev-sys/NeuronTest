package com.example.neurontest.app.di

import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    localStorageModule,
    viewModelModule,
    purchaseModule,
    dispatcherModule
)
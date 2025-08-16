package com.example.neurontest.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.db.NeuronTestDatabase
import com.example.data.local.repository.LocalStorageRepositoryImpl
import com.example.domain.repository.LocalStorageRepository
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.SetUserUseCase
import com.example.neurontest.ui.theme.NeuronTestTheme
import org.koin.dsl.module
import kotlin.math.sin

val localStorageModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(), NeuronTestDatabase::class.java, "NeuronTest.db"
        ).build()
    }

    single {
        get<NeuronTestDatabase>().userDao()
    }

    single<LocalStorageRepository> {
        LocalStorageRepositoryImpl(get())
    }

    factory {
        GetUserUseCase(get())
    }

    factory {
        SetUserUseCase(get())
    }
}
package com.example.neurontest.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.UserDao
import com.example.data.local.db.NeuronTestDatabase
import com.example.data.local.repository.LocalStorageRepositoryImpl
import com.example.domain.repository.LocalStorageRepository
import com.example.domain.usecase.local.DeleteUserUseCase
import com.example.domain.usecase.local.GetUserUseCase
import com.example.domain.usecase.local.SetUserUseCase
import com.example.domain.usecase.local.UpdateUserNameUseCase
import org.koin.dsl.module

val localStorageModule = module {
    single<NeuronTestDatabase> {
        Room.databaseBuilder(
            get<Context>(), NeuronTestDatabase::class.java, "NeuronTest.db"
        ).build()
    }

    single<UserDao> {
        get<NeuronTestDatabase>().userDao()
    }

    single<LocalStorageRepository> {
        LocalStorageRepositoryImpl(get())
    }

    factory<GetUserUseCase> {
        GetUserUseCase(get())
    }

    factory<SetUserUseCase> {
        SetUserUseCase(get())
    }

    factory<UpdateUserNameUseCase> {
        UpdateUserNameUseCase(get())
    }

    factory<DeleteUserUseCase> {
        DeleteUserUseCase(get())
    }
}
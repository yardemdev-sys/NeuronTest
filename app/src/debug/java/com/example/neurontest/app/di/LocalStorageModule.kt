package com.example.neurontest.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.UserDao
import com.example.data.local.db.NeuronTestDatabase
import com.example.data.repository.FakeLocalStorageRepository
import com.example.domain.repository.LocalStorageRepository
import com.example.domain.usecase.DeleteUserUseCase
import com.example.domain.usecase.GetUserUseCase
import com.example.domain.usecase.SetUserUseCase
import com.example.domain.usecase.UpdateUserNameUseCase
import com.example.neurontest.BuildConfig
import org.koin.dsl.module

val localStorageModule = module {
    single {
        Room.databaseBuilder(get<Context>(), NeuronTestDatabase::class.java, "NeuronTest.db")
            .build()
    }
    single<UserDao> { get<NeuronTestDatabase>().userDao() }

    single<LocalStorageRepository> {
        when (BuildConfig.FAKE_USER_MODE) {
            "SUCCESS" -> FakeLocalStorageRepository(
                FakeLocalStorageRepository.Mode.SUCCESS,
                FakeLocalStorageRepository.Mode.SUCCESS
            )
            "NULL" -> FakeLocalStorageRepository(
                FakeLocalStorageRepository.Mode.NULL,
                FakeLocalStorageRepository.Mode.NULL
            )
            "GET_USER_ERROR" -> FakeLocalStorageRepository(
                FakeLocalStorageRepository.Mode.ERROR,
                FakeLocalStorageRepository.Mode.SUCCESS
            )
            "UPDATE_USER_ERROR" -> FakeLocalStorageRepository(
                FakeLocalStorageRepository.Mode.SUCCESS,
                FakeLocalStorageRepository.Mode.ERROR
            )
            else -> FakeLocalStorageRepository(
                FakeLocalStorageRepository.Mode.SUCCESS,
                FakeLocalStorageRepository.Mode.SUCCESS
            )
        }
    }

    factory { GetUserUseCase(get()) }
    factory { SetUserUseCase(get()) }
    factory { UpdateUserNameUseCase(get()) }
    factory { DeleteUserUseCase(get()) }
}

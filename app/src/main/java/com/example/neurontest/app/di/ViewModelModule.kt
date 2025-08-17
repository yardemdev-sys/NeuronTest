package com.example.neurontest.app.di

import com.example.neurontest.ui.screens.purchases.PurchasesViewModel
import com.example.neurontest.ui.screens.registration.RegistrationViewModel
import com.example.neurontest.ui.screens.settings.SettingsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<SettingsViewModel> {
        SettingsViewModel(get(), get(), get(), get())
    }

    viewModel<RegistrationViewModel> {
        RegistrationViewModel(get(), get())
    }

    viewModel<PurchasesViewModel> {
        PurchasesViewModel(get(), get())
    }
}
package com.example.neurontest.app.di

import com.example.neurontest.ui.screens.registration.RegistrationViewModel
import com.example.neurontest.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<SettingsViewModel> {
        SettingsViewModel(get(), get(), get())
    }

    viewModel<RegistrationViewModel> {
        RegistrationViewModel(get())
    }
}
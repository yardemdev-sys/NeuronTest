package com.example.neurontest.ui.screens.registration.model

sealed class RegistrationEffect {
    object SaveUser: RegistrationEffect()
    object ShowSaveError: RegistrationEffect()
    object NavigateBack: RegistrationEffect()
}
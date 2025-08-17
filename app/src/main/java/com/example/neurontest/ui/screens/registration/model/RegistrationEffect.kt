package com.example.neurontest.ui.screens.registration.model

sealed class RegistrationEffect {
    object ShowSaveError: RegistrationEffect()
    object NavigateBack: RegistrationEffect()
    object SaveUser: RegistrationEffect()
}
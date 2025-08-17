package com.example.neurontest.ui.screens.registration.model

sealed class RegistrationIntent {
    data class OnNumberChange(val query: String): RegistrationIntent()
    data class OnCodeChange(val query: String): RegistrationIntent()
    data class OnFirstNameChange(val query: String): RegistrationIntent()
    data class OnLastNameChange(val query: String): RegistrationIntent()

    object SaveUser: RegistrationIntent()
    object ShowSaveError: RegistrationIntent()
    object NavigateBack: RegistrationIntent()
}
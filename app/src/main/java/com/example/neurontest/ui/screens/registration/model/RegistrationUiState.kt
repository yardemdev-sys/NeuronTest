package com.example.neurontest.ui.screens.registration.model

data class RegistrationUiState(
    val bankNumber: String = "",
    val code: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val isBankNumberError: Boolean = false,
    val isCodeError: Boolean = false,
    val isFirstNameError: Boolean = false,
    val isLastNameError: Boolean = false,
    val isContinueButtonActive: Boolean = false
)

package com.example.neurontest.ui.screens.registration.mappers

import com.example.domain.model.User
import com.example.neurontest.ui.screens.registration.model.RegistrationUiState

fun RegistrationUiState.toDomain() = User(
    bankNumber = bankNumber,
    bankCode = code.toInt(),
    firstName = firstName,
    lastName = lastName,
)

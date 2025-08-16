package com.example.neurontest.ui.screens.settings.model

import androidx.annotation.StringRes
import com.example.neurontest.R

data class SettingsUiState(
    val isSaving: Boolean = false,
    val isNameLoading: Boolean = true,
    val isAuth: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    /*
        Для почты, языка и номера телефона используются плейсхолдеры,
        так как их неоткуда получать
     */
    @StringRes val phoneNumber: Int = R.string.phone_placeholder,
    @StringRes val email: Int = R.string.email_placeholder,
    val isEmailConfirmed: Boolean = false,
    // Сохраненные настройки неизвестны - по-уолчанию false
    val isBiometricalAuthEnabled: Boolean = false,
    @StringRes val language: Int = R.string.language,
    val isNameLoadError: Boolean = false
)

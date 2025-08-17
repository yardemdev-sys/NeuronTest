package com.example.neurontest.ui.screens.settings.model

sealed class SettingsIntent {
    data class OnNameChanged(
        val firstName: String,
        val lastName: String
    ): SettingsIntent()
    data class OnSaveName(
        val prevFirstName: String,
        val prevLastName: String
    ): SettingsIntent()
    data class BiometricalAuthToggle(val enabled: Boolean): SettingsIntent()

    object Init: SettingsIntent()
    object Logout: SettingsIntent()
    object ShowLoadingError: SettingsIntent()
    object ShowUpdateError: SettingsIntent()
}

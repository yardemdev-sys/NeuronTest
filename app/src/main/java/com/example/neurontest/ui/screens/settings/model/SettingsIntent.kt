package com.example.neurontest.ui.screens.settings.model

sealed class SettingsIntent {
    data class OnNameChanged(
        val firstName: String,
        val lastName: String
    ): SettingsIntent()
    data class BiometricalAuthToggle(val enabled: Boolean): SettingsIntent()


    object Init: SettingsIntent()
    object ShowLoadingError: SettingsIntent()
}

package com.example.neurontest.ui.screens.settings.model

sealed class SettingsEffect {
    object ShowLoadingError: SettingsEffect()
    object ShowUpdateError: SettingsEffect()
}
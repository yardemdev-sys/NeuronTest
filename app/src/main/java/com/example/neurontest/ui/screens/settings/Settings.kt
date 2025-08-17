package com.example.neurontest.ui.screens.settings

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.neurontest.R
import com.example.neurontest.ui.screens.settings.components.SettingsContent
import com.example.neurontest.ui.screens.settings.model.SettingsEffect
import com.example.neurontest.ui.screens.settings.model.SettingsIntent
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsScreen(
    onNavigateRegister: () -> Unit,
    onNavigatePurchases: () -> Unit,
    vm: SettingsViewModel = koinViewModel()
) {
    val state by vm.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.uiEffect.collect { effect ->
            when (effect) {
                SettingsEffect.ShowLoadingError ->
                    Toast.makeText(context, R.string.get_user_error, Toast.LENGTH_LONG).show()
                SettingsEffect.ShowUpdateError ->
                    Toast.makeText(context, R.string.update_user_error, Toast.LENGTH_LONG).show()

                SettingsEffect.Logout -> vm.logout()
            }
        }
    }

    SettingsContent(
        state = state,
        onChangeName = { firstName, lastName ->
            vm.performIntent(SettingsIntent.OnNameChanged(firstName, lastName))
        },
        onNameSave = { first, last ->
            vm.performIntent(SettingsIntent.OnSaveName(first, last))
        },
        onBack = {},
        onPurchases = onNavigatePurchases,
        onEmail = { },
        onBiometricToggle = { enabled ->
            vm.performIntent(SettingsIntent.BiometricalAuthToggle(enabled))
        },
        onChangeCode = {},
        onRegister = onNavigateRegister,
        onLanguage = {},
        onLogout = {
            vm.performIntent(SettingsIntent.Logout)
        }
    )
}

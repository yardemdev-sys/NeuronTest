package com.example.neurontest.ui.screens.registration.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.neurontest.R
import com.example.neurontest.ui.screens.registration.RegistrationViewModel
import com.example.neurontest.ui.screens.registration.model.RegistrationEffect
import com.example.neurontest.ui.screens.registration.model.RegistrationIntent
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun Registration(
    onBack: () -> Unit,
    onNavigateSettings: () -> Unit,
    vm: RegistrationViewModel = koinViewModel()
) {
    val state by vm.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.uiEffect.collect { effect ->
            when (effect) {
                RegistrationEffect.NavigateBack -> onBack()
                RegistrationEffect.ShowSaveError -> {
                    Toast.makeText(context, R.string.save_error, Toast.LENGTH_LONG)
                }
                RegistrationEffect.SaveUser -> onNavigateSettings()
            }
        }
    }

    RegistrationContent(
        state = state,
        onNumberChange = { query ->
            vm.performIntent(RegistrationIntent.OnNumberChange(query))
        },
        onCodeChange = { query ->
            vm.performIntent(RegistrationIntent.OnCodeChange(query))
        },
        onFirstNameChange = { query ->
            vm.performIntent(RegistrationIntent.OnFirstNameChange(query))
        },
        onLastNameChange = { query ->
            vm.performIntent(RegistrationIntent.OnLastNameChange(query))
        },
        onContinue = { vm.performIntent(RegistrationIntent.SaveUser) },
        onBack = onBack
    )
}
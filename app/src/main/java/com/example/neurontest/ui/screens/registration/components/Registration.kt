package com.example.neurontest.ui.screens.registration.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.neurontest.ui.screens.registration.RegistrationViewModel
import com.example.neurontest.ui.screens.registration.model.RegistrationEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun Registration(
    onBack: () -> Unit,
    vm: RegistrationViewModel = koinViewModel()
) {
    val state by vm.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        vm.uiEffect.collect { effect ->
            when (effect) {
                RegistrationEffect.NavigateBack -> TODO()
                RegistrationEffect.SaveUser -> TODO()
                RegistrationEffect.ShowSaveError -> TODO()
            }
        }
    }
}
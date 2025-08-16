package com.example.neurontest.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetUserUseCase
import com.example.neurontest.ui.screens.settings.model.SettingsEffect
import com.example.neurontest.ui.screens.settings.model.SettingsIntent
import com.example.neurontest.ui.screens.settings.model.SettingsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getUser: GetUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<SettingsEffect>(extraBufferCapacity = 64)
    val uiEffect: SharedFlow<SettingsEffect> = _uiEffect

    private val intentChannel = Channel<SettingsIntent>(Channel.BUFFERED)

    init {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { handle(it) }
        }
        performIntent(SettingsIntent.Init)
    }

    private suspend fun handle(intent: SettingsIntent) {
        _uiState.update { reducer(it, intent) }
        when (intent) {
            SettingsIntent.Init -> {
                _uiState.update {
                    it.copy(
                        isNameLoading = true
                    )
                }
                loadUser()
            }
            is SettingsIntent.ShowLoadingError ->
                _uiEffect.emit(SettingsEffect.ShowLoadingError)
            is SettingsIntent.BiometricalAuthToggle -> {
                _uiState.update {
                    it.copy(
                        isBiometricalAuthEnabled = intent.enabled
                    )
                }
            }
            else -> Unit
        }
    }

    private fun reducer(
        state: SettingsUiState,
        intent: SettingsIntent
    ): SettingsUiState = when (intent) {
        is SettingsIntent.OnNameChanged -> {
            state.copy(
                firstName = intent.firstName,
                lastName = intent.lastName
            )
        }
        else -> state
    }

    private fun loadUser() = viewModelScope.launch(Dispatchers.IO) {
        runCatching { getUser() }
            .onSuccess { user ->
                _uiState.update {
                    it.copy(
                        isAuth = user != null,
                        isNameLoading = false,
                        firstName = user?.firstName.orEmpty(),
                        lastName  = user?.lastName.orEmpty()
                    )
                }
            }
            .onFailure {
                _uiEffect.tryEmit(SettingsEffect.ShowLoadingError)
            }
    }

    fun performIntent(intent: SettingsIntent) {
        intentChannel.trySend(intent)
    }
}

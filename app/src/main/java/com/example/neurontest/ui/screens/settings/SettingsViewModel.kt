package com.example.neurontest.ui.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.local.DeleteUserUseCase
import com.example.domain.usecase.local.GetUserUseCase
import com.example.domain.usecase.local.UpdateUserNameUseCase
import com.example.neurontest.ui.screens.settings.model.SettingsEffect
import com.example.neurontest.ui.screens.settings.model.SettingsIntent
import com.example.neurontest.ui.screens.settings.model.SettingsUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel(
    private val getUser: GetUserUseCase,
    private val updateUserName: UpdateUserNameUseCase,
    private val deleteUser: DeleteUserUseCase,
    private val io: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<SettingsEffect>(extraBufferCapacity = 64)
    val uiEffect: SharedFlow<SettingsEffect> = _uiEffect

    private val intents = MutableSharedFlow<SettingsIntent>(extraBufferCapacity = 64)

    init {
        viewModelScope.launch {
            intents.collect { handle(it) }
        }
        performIntent(SettingsIntent.Init)
    }

    fun performIntent(intent: SettingsIntent) {
        intents.tryEmit(intent)
    }

    private suspend fun handle(intent: SettingsIntent) = when (intent) {
        SettingsIntent.Init -> {
            _uiState.update { it.copy(isNameLoading = true) }
            loadUser()
        }

        is SettingsIntent.OnNameChanged -> {
            _uiState.update { it.copy(firstName = intent.firstName, lastName = intent.lastName) }
        }

        is SettingsIntent.OnSaveName -> {
            val current = uiState.value

            _uiState.update { it.copy(isSaving = true) }

            val result = withContext(io) {
                runCatching { updateUserName(current.firstName, current.lastName) }
            }

            result.onSuccess {
                _uiState.update { it.copy(isSaving = false) }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        isSaving = false,
                        firstName = intent.prevFirstName,
                        lastName = intent.prevLastName
                    )
                }
                _uiEffect.emit(SettingsEffect.ShowUpdateError)
            }
        }

        is SettingsIntent.BiometricalAuthToggle -> {
            _uiState.update { it.copy(isBiometricalAuthEnabled = intent.enabled) }
        }

        is SettingsIntent.ShowLoadingError -> {
            _uiEffect.emit(SettingsEffect.ShowLoadingError)
        }

        SettingsIntent.ShowUpdateError -> {
            _uiEffect.emit(SettingsEffect.ShowUpdateError)
        }

        SettingsIntent.Logout ->
            _uiEffect.emit(SettingsEffect.Logout)
    }

    private suspend fun loadUser() {
        val result = withContext(io) { runCatching { getUser() } }
        result.onSuccess { user ->
            _uiState.update {
                it.copy(
                    isAuth = user != null,
                    isNameLoading = false,
                    firstName = user?.firstName.orEmpty(),
                    lastName = user?.lastName.orEmpty()
                )
            }
        }.onFailure {
            _uiState.update { it.copy(isNameLoadError = true, isNameLoading = false) }
            _uiEffect.emit(SettingsEffect.ShowLoadingError)
        }
    }

    fun logout() {
        viewModelScope.launch(io) {
            deleteUser()
            loadUser()
        }
    }
}

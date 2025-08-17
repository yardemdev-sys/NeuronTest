package com.example.neurontest.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.SetUserUseCase
import com.example.neurontest.ui.screens.registration.mappers.toDomain
import com.example.neurontest.ui.screens.registration.model.RegistrationEffect
import com.example.neurontest.ui.screens.registration.model.RegistrationIntent
import com.example.neurontest.ui.screens.registration.model.RegistrationUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val saveUser: SetUserUseCase,
    private val io: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<RegistrationEffect>(extraBufferCapacity = 64)
    val uiEffect: SharedFlow<RegistrationEffect> = _uiEffect

    private val intents = MutableSharedFlow<RegistrationIntent>(extraBufferCapacity = 64)

    init {
        viewModelScope.launch {
            intents.collect { handle(it) }
        }
    }

    fun performIntent(intent: RegistrationIntent) {
        intents.tryEmit(intent)
    }

    private suspend fun handle(intent: RegistrationIntent) = when (intent) {
        is RegistrationIntent.OnCodeChange -> {
            val q = intent.query.trim()
            _uiState.update { it.copy(code = q, isCodeError = q.isBlank()) }
            checkContinueEnabled()
        }
        is RegistrationIntent.OnFirstNameChange -> {
            val q = intent.query.trim()
            _uiState.update { it.copy(firstName = q, isFirstNameError = q.isBlank()) }
            checkContinueEnabled()
        }
        is RegistrationIntent.OnLastNameChange -> {
            val q = intent.query.trim()
            _uiState.update { it.copy(lastName = q, isLastNameError = q.isBlank()) }
            checkContinueEnabled()
        }
        is RegistrationIntent.OnNumberChange -> {
            val digits = intent.query.filter(Char::isDigit).take(16)
            _uiState.update { it.copy(bankNumber = digits, isBankNumberError = digits.length != 16) }
            checkContinueEnabled()
        }
        RegistrationIntent.SaveUser -> saveUser()
        RegistrationIntent.ShowSaveError -> _uiEffect.emit(RegistrationEffect.ShowSaveError)
        RegistrationIntent.NavigateBack -> _uiEffect.emit(RegistrationEffect.NavigateBack)
    }

    private fun checkContinueEnabled() {
        val s = _uiState.value
        val enabled = !s.isCodeError && !s.isBankNumberError && !s.isFirstNameError && !s.isLastNameError
        _uiState.update { it.copy(isContinueButtonActive = enabled) }
    }

    private fun saveUser() {
        viewModelScope.launch(io) {
            runCatching { saveUser(_uiState.value.toDomain()) }
                .onSuccess { _uiEffect.emit(RegistrationEffect.NavigateBack) }
                .onFailure { _uiEffect.emit(RegistrationEffect.ShowSaveError) }
        }
    }
}

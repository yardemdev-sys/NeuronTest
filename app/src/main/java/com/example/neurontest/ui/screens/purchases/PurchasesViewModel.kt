package com.example.neurontest.ui.screens.purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.purchase.GetPurchasesUseCase
import com.example.neurontest.ui.screens.purchases.mappers.toSection
import com.example.neurontest.ui.screens.purchases.models.PurchasesEffect
import com.example.neurontest.ui.screens.purchases.models.PurchasesIntent
import com.example.neurontest.ui.screens.purchases.models.PurchasesUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PurchasesViewModel(
    private val io: CoroutineDispatcher = Dispatchers.IO,
    private val getPurchases: GetPurchasesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(PurchasesUiState())
    val uiState: StateFlow<PurchasesUiState> = _uiState

    private val _uiEffect = MutableSharedFlow<PurchasesEffect>(extraBufferCapacity = 64)
    val uiEffect: SharedFlow<PurchasesEffect> = _uiEffect

    private val intents = MutableSharedFlow<PurchasesIntent>(extraBufferCapacity = 64)

    init {
        viewModelScope.launch {
            intents.collect { handle(it) }
        }
        performIntent(PurchasesIntent.LoadPurchases)
    }

    suspend fun handle(intent: PurchasesIntent) = when (intent) {
        PurchasesIntent.NavigateBack ->
            _uiEffect.emit(PurchasesEffect.NavigateBack)
        PurchasesIntent.ShowLoadingError ->
            _uiEffect.emit(PurchasesEffect.ShowLoadingError)

        PurchasesIntent.LoadPurchases -> loadPurchases()
    }

    fun performIntent(intent: PurchasesIntent) {
        intents.tryEmit(intent)
    }

    fun loadPurchases() {
        viewModelScope.launch(io) {
            getPurchases()
                .map { it.toSection() }
                .onStart { _uiState.update { it.copy(isLoading = true) } }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false) }
                    _uiEffect.emit(PurchasesEffect.ShowLoadingError)
                }
                .collectLatest { sections ->
                    _uiState.update { it.copy(isLoading = false, sections = sections) }
                }
        }
    }
}
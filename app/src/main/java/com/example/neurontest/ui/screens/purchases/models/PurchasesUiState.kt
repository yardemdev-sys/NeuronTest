package com.example.neurontest.ui.screens.purchases.models

data class PurchasesUiState(
    val isLoading: Boolean = true,
    val sections: List<Section> = emptyList<Section>()
)

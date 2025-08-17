package com.example.neurontest.ui.screens.purchases.models

sealed class PurchasesEffect {
    object ShowLoadingError: PurchasesEffect()
    object NavigateBack: PurchasesEffect()
}
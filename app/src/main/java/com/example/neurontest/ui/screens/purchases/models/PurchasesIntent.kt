package com.example.neurontest.ui.screens.purchases.models

sealed class PurchasesIntent {
    object ShowLoadingError: PurchasesIntent()
    object NavigateBack: PurchasesIntent()
    object LoadPurchases: PurchasesIntent()
}
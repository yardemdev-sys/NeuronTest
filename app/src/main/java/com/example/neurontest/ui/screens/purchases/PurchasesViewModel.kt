package com.example.neurontest.ui.screens.purchases

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.purchase.GetPurchasesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PurchasesViewModel(
    private val io: CoroutineDispatcher = Dispatchers.IO,
    private val getPurchases: GetPurchasesUseCase
): ViewModel() {
}
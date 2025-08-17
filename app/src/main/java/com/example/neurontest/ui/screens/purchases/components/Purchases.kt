package com.example.neurontest.ui.screens.purchases.components

import androidx.compose.runtime.Composable
import com.example.domain.model.Purchase
import com.example.neurontest.ui.screens.purchases.PurchasesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun Purchases(
    onBack: () -> Unit,
    vm: PurchasesViewModel = koinViewModel()
) {

}

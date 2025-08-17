package com.example.domain.usecase.purchase

import com.example.domain.model.Purchase
import com.example.domain.repository.PurchaseRepository
import kotlinx.coroutines.flow.Flow

class GetPurchasesUseCase(
    private val repository: PurchaseRepository
) {
    operator fun invoke(): Flow<List<Purchase>> = repository.getPurchases()
}
package com.example.domain.repository

import com.example.domain.model.Purchase
import kotlinx.coroutines.flow.Flow

interface PurchaseRepository {
    fun getPurchases(): Flow<List<Purchase>>
}
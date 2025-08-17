package com.example.data.purchase.mappers

import com.example.data.purchase.model.PurchaseDTO
import com.example.domain.model.Purchase

fun PurchaseDTO.toDomain() = Purchase(
    date = date,
    name = name
)

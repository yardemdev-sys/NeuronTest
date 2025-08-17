package com.example.data.purchase.model

import kotlinx.serialization.Serializable

@Serializable
data class Root(
    val data: List<PurchaseDTO>
)

@Serializable
data class PurchaseDTO(
    val date: String,
    val name: List<String>
)

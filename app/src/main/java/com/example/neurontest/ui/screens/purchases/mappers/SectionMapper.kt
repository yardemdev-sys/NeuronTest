package com.example.neurontest.ui.screens.purchases.mappers

import com.example.domain.model.Purchase
import com.example.neurontest.ui.screens.purchases.models.Section

fun List<Purchase>.toSection() =
    groupBy { it.date }
        .toSortedMap(compareByDescending { it })
        .map { (date, purchases) ->
            Section(
                date = date,
                items = purchases.flatMap { it.name }
            )
        }

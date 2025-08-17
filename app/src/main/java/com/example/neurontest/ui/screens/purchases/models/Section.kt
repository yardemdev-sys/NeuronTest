package com.example.neurontest.ui.screens.purchases.models

import java.time.LocalDate

data class Section(
    val date: LocalDate,
    val items: List<String>
)
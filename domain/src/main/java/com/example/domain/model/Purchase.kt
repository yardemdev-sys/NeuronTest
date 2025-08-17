package com.example.domain.model

import java.time.LocalDate

data class Purchase(
    val date: LocalDate,
    val name: List<String>
)

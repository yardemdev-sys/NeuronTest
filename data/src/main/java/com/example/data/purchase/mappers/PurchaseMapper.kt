package com.example.data.purchase.mappers

import com.example.data.purchase.model.PurchaseDTO
import com.example.domain.model.Purchase
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeParseException


fun PurchaseDTO.toDomain(zone: ZoneId = ZoneId.systemDefault()): Purchase {
    val localDate = try {
        Instant.parse(date).atZone(zone).toLocalDate()
    } catch (_: DateTimeParseException) {
        LocalDate.parse(date)
    }
    return Purchase(date = localDate, name = name)
}

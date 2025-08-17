package com.example.data.local.mapper

import com.example.data.local.entity.UserEntity
import com.example.domain.model.User

fun UserEntity.toDomain() = User(
    bankNumber = bankNumber,
    bankCode = bankCode,
    firstName = firstName,
    lastName = lastName
)

fun User.toEntity() = UserEntity(
    bankNumber = bankNumber,
    bankCode = bankCode,
    firstName = firstName,
    lastName = lastName
)

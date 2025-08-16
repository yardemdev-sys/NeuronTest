package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    /*
        Запись с данными пользователя гарантированно единственная,
        поэтому я использую фиксированный id = 1
     */
    @PrimaryKey val id: Int = 1,
    val bankNumber: Int,
    val bankCode: Int,
    val firstName: String,
    val lastName: String
)

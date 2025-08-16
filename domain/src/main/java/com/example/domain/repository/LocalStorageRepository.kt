package com.example.domain.repository

import com.example.domain.model.User

interface LocalStorageRepository {
    suspend fun getUser(): User?

    suspend fun setUser(user: User)

    suspend fun updateUserName(
        firstName: String,
        lastName: String
    )
}

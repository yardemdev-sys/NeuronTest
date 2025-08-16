package com.example.data.repository

import com.example.domain.model.User
import com.example.domain.repository.LocalStorageRepository

class FakeLocalStorageRepository(
    private val getUserMode: Mode,
    private val updateUserMode: Mode
) : LocalStorageRepository {
    enum class Mode { SUCCESS, ERROR, NULL }
    override suspend fun getUser(): User? = when (getUserMode) {
        Mode.SUCCESS ->  User(
            bankNumber = "0000000000000000",
            bankCode = 1,
            firstName = "Test",
            lastName = "Test"
        )
        Mode.ERROR -> throw RuntimeException("Get user exception")
        Mode.NULL -> null
    }

    override suspend fun setUser(user: User) {
    }

    override suspend fun updateUserName(firstName: String, lastName: String) {
        when (updateUserMode) {
            Mode.ERROR -> throw RuntimeException("Update user exception")
            else -> Unit
        }
    }
}
package com.example.data.local.repository

import com.example.data.local.dao.UserDao
import com.example.data.local.mapper.toDomain
import com.example.data.local.mapper.toEntity
import com.example.domain.model.User
import com.example.domain.repository.LocalStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalStorageRepositoryImpl(
    private val dao: UserDao
): LocalStorageRepository {
    override suspend fun getUser(): User? =
        dao.getUser()?.toDomain()

    override suspend fun setUser(user: User) =
        dao.upsertUser(user.toEntity())

    override suspend fun updateUserName(firstName: String, lastName: String) {
        dao.updateUserName(firstName, lastName)
    }
}
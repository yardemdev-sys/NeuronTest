package com.example.domain.usecase.local

import com.example.domain.model.User
import com.example.domain.repository.LocalStorageRepository

class SetUserUseCase(private val repository: LocalStorageRepository) {
    suspend operator fun invoke(user: User) = repository.setUser(user)
}

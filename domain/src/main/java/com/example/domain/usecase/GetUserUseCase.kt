package com.example.domain.usecase

import com.example.domain.model.User
import com.example.domain.repository.LocalStorageRepository

class GetUserUseCase(private val repository: LocalStorageRepository) {
    suspend operator fun invoke(): User? = repository.getUser()
}

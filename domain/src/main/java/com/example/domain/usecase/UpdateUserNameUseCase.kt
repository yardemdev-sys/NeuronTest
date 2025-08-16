package com.example.domain.usecase

import com.example.domain.repository.LocalStorageRepository

class UpdateUserNameUseCase(
    private val repository: LocalStorageRepository
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String
    ) = repository.updateUserName(firstName, lastName)
}
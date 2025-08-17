package com.example.domain.usecase

import com.example.domain.repository.LocalStorageRepository

class DeleteUserUseCase(
    private val repository: LocalStorageRepository
) {
    suspend operator fun invoke() = repository.deleteUser()
}
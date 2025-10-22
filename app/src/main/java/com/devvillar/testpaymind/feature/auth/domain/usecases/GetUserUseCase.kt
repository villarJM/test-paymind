package com.devvillar.testpaymind.feature.auth.domain.usecases

import com.devvillar.testpaymind.feature.auth.domain.models.User
import com.devvillar.testpaymind.feature.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(userId: Int): Result<User> {
        return repository.getUser(userId)
    }
}
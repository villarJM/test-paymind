package com.devvillar.testpaymind.feature.auth.domain.usecases

import com.devvillar.testpaymind.feature.auth.domain.models.UserSession
import com.devvillar.testpaymind.feature.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(username: String, password: String): Result<UserSession> {
        return repository.login(username, password)
    }
}
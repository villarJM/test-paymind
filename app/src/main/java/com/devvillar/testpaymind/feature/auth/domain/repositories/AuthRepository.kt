package com.devvillar.testpaymind.feature.auth.domain.repositories

import com.devvillar.testpaymind.feature.auth.domain.models.User
import com.devvillar.testpaymind.feature.auth.domain.models.UserSession

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<UserSession>
    suspend fun getUser(userId: Int): Result<User>
}
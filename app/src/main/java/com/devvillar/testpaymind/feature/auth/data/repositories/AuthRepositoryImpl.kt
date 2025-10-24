package com.devvillar.testpaymind.feature.auth.data.repositories

import com.devvillar.testpaymind.feature.auth.data.datasources.remote.AuthRemoteDataSource
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.mappers.toDomain
import com.devvillar.testpaymind.feature.auth.domain.models.User
import com.devvillar.testpaymind.feature.auth.domain.models.UserSession
import com.devvillar.testpaymind.feature.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
): AuthRepository {

    override suspend fun login(username: String, password: String): Result<UserSession> {
        return try {
            val response = remoteDataSource.login(username, password)
            response.fold(
                onSuccess = { apiResponse ->
                    val userSessionDto = apiResponse.data
                    if (userSessionDto == null) {
                        return Result.failure(Exception("No user session data"))
                    }
                    val userSession = userSessionDto.toDomain()
                    Result.success(userSession)
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUser(userId: Int): Result<User> {
        return try {
            val response = remoteDataSource.getUser(userId)
            response.fold(
                onSuccess = { apiResponse ->
                    val userDto = apiResponse.data
                    if (userDto == null) {
                        return Result.failure(Exception("No user data"))
                    }
                    val user = userDto.toDomain()
                    Result.success(user)
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
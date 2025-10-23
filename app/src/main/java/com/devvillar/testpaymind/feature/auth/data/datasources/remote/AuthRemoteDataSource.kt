package com.devvillar.testpaymind.feature.auth.data.datasources.remote

import com.devvillar.testpaymind.core.network.ApiResponse
import com.devvillar.testpaymind.core.network.ApiService
import com.devvillar.testpaymind.core.network.NetworkClient
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.request.LoginRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val networkClient: NetworkClient
) {
    suspend fun login(username: String, password: String): Result<ApiResponse<UserSessionDto>> {
        val request = LoginRequest(username, password)
        return networkClient.safeApiCall {
            apiService.login(request)
        }
    }

    suspend fun getUser(userId: Int): Result<ApiResponse<UserDto>> {
        return networkClient.safeApiCall {
            apiService.getUser(userId)
        }
    }
}
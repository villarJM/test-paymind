package com.devvillar.testpaymind.core.network

import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<UserSessionDto>

    @GET("/auth/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): ApiResponse<UserDto>

    @GET("/merchants-management/merchant/{merchantId}/sub-merchant/{subMerchantId}/transactions")
    suspend fun getTransactions(@Path("merchantId") merchantId: Int, @Path("subMerchantId") subMerchantId: Int): ApiResponse<List<Any>>
}
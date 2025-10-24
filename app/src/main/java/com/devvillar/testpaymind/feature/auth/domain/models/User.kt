package com.devvillar.testpaymind.feature.auth.domain.models

data class User(
    val createdAt: Long,
    val createdBy: Int,
    val updatedAt: Long,
    val updatedBy: Int,
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val role: String,
    val attempts: Int,
    val status: String,
    val enabled: Boolean,
    val activated: Boolean,
    val roleId: Int,
    val createApiKey: Boolean,
    val userCredentials: UserCredential,
)

data class UserCredential(
    val userId: Int,
    val subMerchantId: Int,
    val merchantId: Int,
)
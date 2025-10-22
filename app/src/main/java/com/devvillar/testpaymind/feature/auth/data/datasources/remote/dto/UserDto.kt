package com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("createdBy") val createdBy: Int,
    @SerializedName("updatedAt") val updatedAt: Long,
    @SerializedName("updatedBy") val updatedBy: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String,
    @SerializedName("attempts") val attempts: Int,
    @SerializedName("status") val status: String,
    @SerializedName("enabled") val enabled: Boolean,
    @SerializedName("activated") val activated: Boolean,
    @SerializedName("roleId") val roleId: Int,
    @SerializedName("createApiKey") val createApiKey: Boolean,
    @SerializedName("userCredential") val userCredentials: UserCredentialDto,
)

data class UserCredentialDto(
    @SerializedName("userId") val userId: Int,
    @SerializedName("subMerchantId") val subMerchantId: Int,
    @SerializedName("merchantId") val merchantId: Int,
)
package com.devvillar.testpaymind.feature.auth.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UserSessionDto(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("expiredDate") val expiredDate: Long,
    @SerializedName("userId") val userId: Int,
    @SerializedName("valid") val valid: Boolean,
)
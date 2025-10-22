package com.devvillar.testpaymind.feature.auth.domain.models

data class UserSession(
    val accessToken: String,
    val expiredDate: Long,
    val userId: Int,
    val valid: Boolean,

) {
    override fun toString(): String = "UserSession(accessToken=*****, expiredDate=$expiredDate, userId=$userId, valid=$valid)"
}

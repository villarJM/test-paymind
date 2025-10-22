package com.devvillar.testpaymind.feature.auth.data.mappers

import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserCredentialDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.testpaymind.feature.auth.domain.models.User
import com.devvillar.testpaymind.feature.auth.domain.models.UserCredential
import com.devvillar.testpaymind.feature.auth.domain.models.UserSession
import javax.inject.Inject

class AuthMapper @Inject constructor() {

    fun toDomain(dto: UserSessionDto): UserSession {
        return UserSession(
            accessToken = dto.accessToken,
            expiredDate = dto.expiredDate,
            userId = dto.userId,
            valid = dto.valid
        )
    }

    fun toDomainUser(dto: UserDto): User {
        return User(
            createdAt = dto.createdAt,
            createdBy = dto.createdBy,
            updatedAt = dto.updatedAt,
            updatedBy = dto.updatedBy,
            id = dto.id,
            name = dto.name,
            email = dto.email,
            username = dto.username,
            role = dto.role,
            attempts = dto.attempts,
            status = dto.status,
            enabled = dto.enabled,
            activated = dto.activated,
            roleId = dto.roleId,
            createApiKey = dto.createApiKey,
            userCredentials = toDomainUserCredential(dto.userCredentials)
        )
    }

    fun toDomainUserCredential(dto: UserCredentialDto): UserCredential {
        return UserCredential(
            userId = dto.userId,
            subMerchantId = dto.subMerchantId,
            merchantId = dto.merchantId
        )
    }

}
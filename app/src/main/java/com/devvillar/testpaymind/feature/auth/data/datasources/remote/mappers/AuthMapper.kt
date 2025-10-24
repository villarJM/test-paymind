package com.devvillar.testpaymind.feature.auth.data.datasources.remote.mappers

import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserCredentialDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserDto
import com.devvillar.testpaymind.feature.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.testpaymind.feature.auth.domain.models.User
import com.devvillar.testpaymind.feature.auth.domain.models.UserCredential
import com.devvillar.testpaymind.feature.auth.domain.models.UserSession
import javax.inject.Inject

fun UserSessionDto.toDomain(): UserSession {
    return UserSession(
        accessToken = accessToken,
        expiredDate = expiredDate,
        userId = userId,
        valid = valid
    )
}

fun UserDto.toDomain(): User {
    return User(
        createdAt = createdAt,
        createdBy = createdBy,
        updatedAt = updatedAt,
        updatedBy = updatedBy,
        id = id,
        name = name,
        email = email,
        username = username,
        role = role,
        attempts = attempts,
        status = status,
        enabled = enabled,
        activated = activated,
        roleId = roleId,
        createApiKey = createApiKey,
        userCredentials = userCredentials.toDomain()
    )
}

fun UserCredentialDto.toDomain(): UserCredential {
    return UserCredential(
        userId = userId,
        subMerchantId = subMerchantId,
        merchantId = merchantId
    )
}

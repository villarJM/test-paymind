package com.devvillar.testpaymind.core.network

import com.devvillar.testpaymind.core.error.ErrorHandler
import javax.inject.Inject

class NetworkClient @Inject constructor (
    private val errorHandler: ErrorHandler
) {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            val result = apiCall()
            Result.success(result)
        } catch (e: Exception) {
            val errorMessage = errorHandler.getErrorMessage(e)
            Result.failure(Exception(errorMessage, e))
        }
    }
}
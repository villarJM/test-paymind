package com.devvillar.testpaymind.core.network.interceptors

import com.devvillar.testpaymind.core.utils.PrefsManager
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val prefsManager: PrefsManager
) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val accessToken = prefsManager.getAccessToken()
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .header("Accept", "application/json")

        if (originalRequest.body != null) {
            requestBuilder.header("Content-Type", "application/json")
        }

        if (!accessToken.isNullOrEmpty()) {
            requestBuilder.header("Authorization", "Bearer $accessToken")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}
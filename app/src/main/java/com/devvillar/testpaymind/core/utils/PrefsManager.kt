package com.devvillar.testpaymind.core.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefsManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    companion object {
        private const val PREFS_NAME = "test_pay_mind_prefs"
        private const val ACCESS_TOKEN_KEY = "access_token"
        private const val REFRESH_TOKEN_KEY = "refresh_token"
        private const val USER_ID_KEY = "user_id"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getAccessToken(): String? {
        return prefs.getString(ACCESS_TOKEN_KEY, null)
    }

    fun getRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN_KEY, null)
    }

    fun getUserId(): Int? {
        return prefs.getInt(USER_ID_KEY, 0)
    }

    fun saveTokens(accessToken: String, refreshToken: String) {
        prefs.edit().apply {
            putString(ACCESS_TOKEN_KEY, accessToken)
            putString(REFRESH_TOKEN_KEY, refreshToken)
            apply()
        }
    }

    fun saveUserId(userId: Int) {
        prefs.edit().apply {
            putInt(USER_ID_KEY, userId)
            apply()
        }
    }

    fun clearTokens() {
        prefs.edit().apply {
            remove(ACCESS_TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
            remove(USER_ID_KEY)
            apply()
        }
    }
}
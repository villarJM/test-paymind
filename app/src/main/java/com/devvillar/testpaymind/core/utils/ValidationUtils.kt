package com.devvillar.testpaymind.core.utils

import android.content.Context
import com.devvillar.testpaymind.R
import com.devvillar.testpaymind.feature.auth.domain.models.ValidationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ValidationUtils @Inject constructor(
    @param:ApplicationContext private val context: Context
) {

    companion object {
        const val MIN_PASSWORD_LENGTH = 6
        val SPECIAL_CHARACTERS = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '[', ']', '{', '}', '|', ';', ':', ',', '.', '<', '>', '?')

        const val FIELD_USERNAME = "username"
        const val FIELD_PASSWORD = "password"
    }

    private fun getUsernameEmptyError(): String = context.getString(R.string.login_empty_username)
    private fun getUsernameInvalidError(): String = context.getString(R.string.login_invalid_username)
    private fun getPasswordEmptyError(): String = context.getString(R.string.login_empty_password)
    private fun getPasswordInvalidError(): String =
        context.getString(R.string.login_invalid_password, MIN_PASSWORD_LENGTH)

    fun validateFieldLogins(username: String?, password: String?): ValidationResult {
        val errors = mutableMapOf<String, String>()
        validateUsername(username)?.let { errors[FIELD_USERNAME] = it }
        validatePassword(password)?.let { errors[FIELD_PASSWORD] = it }

        return ValidationResult(errors)
    }

    fun validateUsername(username: String?): String? = when {
        username.isNullOrBlank() -> getUsernameEmptyError()
        username.contains(" ") -> getUsernameInvalidError()
        else -> null
    }

    private fun isValidUsername(username: String): Boolean {
        return username == username.trim()
    }

    fun validatePassword(password: String?): String? = when {
        password.isNullOrBlank() -> getPasswordEmptyError()
        !isValidPassword(password) -> getPasswordInvalidError()
        else -> null
    }

    fun isValidPassword(password: String?): Boolean {
        if (password == null || password.length < MIN_PASSWORD_LENGTH) return false

        //var hasUpper = false
        var hasLower = false
        var hasDigit = false
        //var hasSpecial = false

        for (char in password) {
            when {
                //char.isUpperCase() -> hasUpper = true
                char.isLowerCase() -> hasLower = true
                char.isDigit() -> hasDigit = true
                //char in SPECIAL_CHARACTERS -> hasSpecial = true
            }
            //if (hasUpper && hasLower && hasDigit && hasSpecial) return true
            if (hasLower && hasDigit) return true
        }
        return false
    }
}
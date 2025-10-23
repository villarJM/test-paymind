package com.devvillar.testpaymind.feature.auth.presentation.states

sealed class LoginUIState {
    object Initial : LoginUIState()
    object Loading : LoginUIState()
    object Success: LoginUIState()
    data class Error(val message: String) : LoginUIState()
}
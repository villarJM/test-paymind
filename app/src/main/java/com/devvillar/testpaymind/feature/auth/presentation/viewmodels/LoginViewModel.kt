package com.devvillar.testpaymind.feature.auth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.devvillar.testpaymind.core.base.BaseViewModel
import com.devvillar.testpaymind.core.utils.PrefsManager
import com.devvillar.testpaymind.core.utils.ValidationUtils
import com.devvillar.testpaymind.feature.auth.domain.models.ValidationResult
import com.devvillar.testpaymind.feature.auth.domain.usecases.LoginUseCase
import com.devvillar.testpaymind.feature.auth.presentation.states.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val prefManager: PrefsManager,
    private val validationUtils: ValidationUtils
): BaseViewModel() {

    private val _loginUIState = MutableStateFlow<LoginUIState>(LoginUIState.Initial)
    val loginUIState: StateFlow<LoginUIState> = _loginUIState.asStateFlow()

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    fun login(username: String, password: String) {
        val validation = validationUtils.validateFieldLogins(username, password)
        if (!validation.isValid) {
            _validationResult.value = validation
            return
        }

        viewModelScope.launch {
            _loginUIState.value = LoginUIState.Loading

            loginUseCase(username, password).fold(
                onSuccess = { userSession ->
                    prefManager.saveTokens(userSession.accessToken, "")
                    _loginUIState.value = LoginUIState.Success
                },
                onFailure = { error ->
                    _loginUIState.value = LoginUIState.Error(error.message.orEmpty())
                }
            )
        }
    }

    fun clearValidationErrors() {
        _validationResult.value = ValidationResult()
    }
}
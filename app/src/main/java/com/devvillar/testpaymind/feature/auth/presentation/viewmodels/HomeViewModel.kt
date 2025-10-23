package com.devvillar.testpaymind.feature.auth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.devvillar.testpaymind.core.base.BaseViewModel
import com.devvillar.testpaymind.core.states.UIState
import com.devvillar.testpaymind.core.utils.PrefsManager
import com.devvillar.testpaymind.feature.auth.domain.models.User
import com.devvillar.testpaymind.feature.auth.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val prefManager: PrefsManager
): BaseViewModel() {

    private val _homeUIState = MutableStateFlow<UIState<User>>(UIState.Initial)
    val homeUIState: StateFlow<UIState<User>> = _homeUIState.asStateFlow()

    fun getUser() {

        val userId = prefManager.getUserId()
        if (userId == null || userId == 0) {
            _homeUIState.value = UIState.Error("User ID not found")
            return
        }

        viewModelScope.launch {
            _homeUIState.value = UIState.Loading

            getUserUseCase(userId).fold(
                onSuccess = { user ->
                    _homeUIState.value = UIState.Success(user)
                },
                onFailure = { error ->
                    _homeUIState.value = UIState.Error(error.message.orEmpty())
                }
            )
        }
    }

    fun logout() {
        prefManager.clearTokens()
    }
}
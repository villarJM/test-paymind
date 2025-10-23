package com.devvillar.testpaymind.core.session

import com.devvillar.testpaymind.core.base.BaseViewModel
import com.devvillar.testpaymind.core.utils.PrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val prefsManager: PrefsManager
): BaseViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    init {
        val accessToken = prefsManager.getAccessToken()
        _isLoggedIn.value = !accessToken.isNullOrEmpty()
    }

    fun updateLoginStatus(accessToken: String?) {
        if (accessToken.isNullOrEmpty()) {
            _isLoggedIn .value = false
            return
        }
        _isLoggedIn.value = true
        prefsManager.saveTokens(accessToken, "")
    }

    fun logout() {
        prefsManager.clearTokens()
        _isLoggedIn.value = false
    }
}
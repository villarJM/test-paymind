package com.devvillar.testpaymind.feature.transaction.presentation.states

import com.devvillar.testpaymind.feature.transaction.domain.models.TransactionData

sealed class TransactionUIState {
    object Initial : TransactionUIState()
    object Loading : TransactionUIState()
    data class Success(val data: TransactionData): TransactionUIState()
    data class Error(val message: String) : TransactionUIState()
}
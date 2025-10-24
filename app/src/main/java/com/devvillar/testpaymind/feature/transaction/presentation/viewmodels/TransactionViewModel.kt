package com.devvillar.testpaymind.feature.transaction.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.devvillar.testpaymind.core.base.BaseViewModel
import com.devvillar.testpaymind.core.utils.PrefsManager
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.request.PageableParamsRequest
import com.devvillar.testpaymind.feature.transaction.domain.usecases.GetTransactionByMerchantAndSubMerchantUseCase
import com.devvillar.testpaymind.feature.transaction.presentation.states.TransactionUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getTransactionByMerchantAndSubMerchantUseCase: GetTransactionByMerchantAndSubMerchantUseCase,
    private val prefsManager: PrefsManager
): BaseViewModel() {

    private val _transactionUIState = MutableStateFlow<TransactionUIState>(TransactionUIState.Initial)
    val transactionUIState = _transactionUIState.asStateFlow()

    private fun getMerchantId(): Int = 20
    private fun getSubMerchantId(): Int = 21

    private val _page = MutableStateFlow(0)
    val page = _page.asStateFlow()

    private val _pageSize = MutableStateFlow(10)
    val pageSize = _pageSize.asStateFlow()

    private val _sort = MutableStateFlow("date,desc")
    val sort = _sort.asStateFlow()

    // Inicializar con el primer día del mes actual
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val startOfMonth = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
    }
    private val today = Calendar.getInstance()

    private val _startDate = MutableStateFlow(dateFormat.format(startOfMonth.time))
    val startDate = _startDate.asStateFlow()

    private val _endDate = MutableStateFlow(dateFormat.format(today.time))
    val endDate = _endDate.asStateFlow()

    fun updatePage(pageNum: Int) { _page.value = pageNum }
    fun updatePageSize(size: Int) { _pageSize.value = size }
    fun updateSort(sortBy: String) { _sort.value = sortBy }
    fun updateStartDate(date: String) { _startDate.value = date }
    fun updateEndDate(date: String) { _endDate.value = date }

    fun getTransactions() {
        getTransactionByMerchantAndSubMerchant(
            merchantId = getMerchantId(),
            subMerchantId = getSubMerchantId(),
            paramsRequest = PageableParamsRequest(
                page = _page.value,
                size = _pageSize.value,
                sort = _sort.value,
                startDate = _startDate.value,
                endDate = _endDate.value
            )
        )
    }

    fun getTransactionByMerchantAndSubMerchant(merchantId: Int, subMerchantId: Int, paramsRequest: PageableParamsRequest) {

        if (merchantId == 0 || subMerchantId == 0) {
            _transactionUIState.value = TransactionUIState.Error("Merchant ID or Sub-Merchant ID is invalid")
            return
        }

        viewModelScope.launch {
            _transactionUIState.value = TransactionUIState.Loading

            getTransactionByMerchantAndSubMerchantUseCase(merchantId, subMerchantId, paramsRequest).fold(
                onSuccess = {
                    _transactionUIState.value = TransactionUIState.Success(it)
                },
                onFailure = { error ->
                    _transactionUIState.value = TransactionUIState.Error(error.message.orEmpty())
                }
            )
        }
    }
}
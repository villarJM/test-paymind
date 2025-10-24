package com.devvillar.testpaymind.feature.transaction.domain.usecases

import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.request.PageableParamsRequest
import com.devvillar.testpaymind.feature.transaction.domain.models.TransactionData
import com.devvillar.testpaymind.feature.transaction.domain.repositories.MerchantsManagementRepository
import javax.inject.Inject

class GetTransactionByMerchantAndSubMerchantUseCase @Inject constructor(
    private val repository: MerchantsManagementRepository
) {

    suspend operator fun invoke(
        merchantId: Int,
        subMerchantId: Int,
        paramsRequest: PageableParamsRequest
    ): Result<TransactionData> {
        return repository.getTransactionByMerchantAndSubMerchant(merchantId, subMerchantId, paramsRequest)
    }
}
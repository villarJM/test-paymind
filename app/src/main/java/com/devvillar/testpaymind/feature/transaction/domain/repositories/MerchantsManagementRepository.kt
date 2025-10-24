package com.devvillar.testpaymind.feature.transaction.domain.repositories

import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.request.PageableParamsRequest
import com.devvillar.testpaymind.feature.transaction.domain.models.TransactionData

interface MerchantsManagementRepository {

    suspend fun getTransactionByMerchantAndSubMerchant(merchantId: Int, subMerchantId: Int, paramsRequest: PageableParamsRequest): Result<TransactionData>
}

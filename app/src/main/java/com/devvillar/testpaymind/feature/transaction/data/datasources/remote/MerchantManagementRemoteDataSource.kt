package com.devvillar.testpaymind.feature.transaction.data.datasources.remote

import android.content.Context
import com.devvillar.testpaymind.core.network.ApiResponse
import com.devvillar.testpaymind.core.network.ApiService
import com.devvillar.testpaymind.core.network.NetworkClient
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.dto.TransactionDataDto
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.request.PageableParamsRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MerchantManagementRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val networkClient: NetworkClient,
    @param:ApplicationContext private val context: Context
) {

    suspend fun getTransactionByMerchantAndSubMerchant(merchantId: Int, subMerchantId: Int, paramsRequest: PageableParamsRequest): Result<ApiResponse<TransactionDataDto>> {
        return networkClient.safeApiCall {
            apiService.getTransactionByMerchantAndSubMerchant(merchantId, subMerchantId, paramsRequest.toMap())
        }
    }
}
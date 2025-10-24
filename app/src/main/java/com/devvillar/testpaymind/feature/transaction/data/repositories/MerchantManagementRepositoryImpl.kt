package com.devvillar.testpaymind.feature.transaction.data.repositories

import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.MerchantManagementRemoteDataSource
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.mappers.toDomain
import com.devvillar.testpaymind.feature.transaction.data.datasources.remote.request.PageableParamsRequest
import com.devvillar.testpaymind.feature.transaction.domain.models.TransactionData
import com.devvillar.testpaymind.feature.transaction.domain.repositories.MerchantsManagementRepository
import javax.inject.Inject

class MerchantManagementRepositoryImpl @Inject constructor(
    private val remoteDataSource: MerchantManagementRemoteDataSource
): MerchantsManagementRepository  {
    override suspend fun getTransactionByMerchantAndSubMerchant(
        merchantId: Int,
        subMerchantId: Int,
        paramsRequest: PageableParamsRequest
    ): Result<TransactionData> {
        return try {
            val response = remoteDataSource.getTransactionByMerchantAndSubMerchant(merchantId, subMerchantId, paramsRequest)
            response.fold(
                onSuccess = { apiResponse ->
                    val transactionDataDto = apiResponse.data
                    if (transactionDataDto == null) {
                        return Result.failure(Exception("No transaction data"))
                    }
                    val transactionData = transactionDataDto.toDomain()
                    Result.success(transactionData)
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}
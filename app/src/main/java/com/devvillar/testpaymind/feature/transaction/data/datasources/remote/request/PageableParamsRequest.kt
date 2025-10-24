package com.devvillar.testpaymind.feature.transaction.data.datasources.remote.request

import com.devvillar.testpaymind.core.utils.DateUtils

data class PageableParamsRequest(
    val page: Int,
    val size: Int,
    val sort: String,
    val startDate: String,
    val endDate: String
) {
    fun toMap(): Map<String, String> {
        val paramsMap = mutableMapOf<String, String>()
        paramsMap["page"] = page.toString()
        paramsMap["size"] = size.toString()
        paramsMap["sort"] = sort
        if (startDate.isNotEmpty()) {
            paramsMap["startDate"] = DateUtils.formatDateForApi(startDate)
        }
        if (endDate.isNotEmpty()) {
            paramsMap["endDate"] = DateUtils.formatDateForApi(endDate)
        }
        return paramsMap
    }
}



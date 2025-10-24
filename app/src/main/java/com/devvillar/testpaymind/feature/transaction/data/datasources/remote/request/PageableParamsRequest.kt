package com.devvillar.testpaymind.feature.transaction.data.datasources.remote.request

data class PageableParamsRequest(
    val page: Int,
    val size: Int,
    val sort: String,
    val startDate: String,
    val endDate: String
)

{
    fun toMap(): Map<String, String> {
        val paramsMap = mutableMapOf<String, String>()
        paramsMap["page"] = page.toString()
        paramsMap["size"] = size.toString()
        paramsMap["sort"] = sort
        if (startDate.isNotEmpty()) {
            paramsMap["startDate"] = startDate
        }
        if (endDate.isNotEmpty()) {
            paramsMap["endDate"] = endDate
        }
        return paramsMap
    }
}

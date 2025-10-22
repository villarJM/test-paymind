package com.devvillar.testpaymind.core.network.exceptions

class NoConnectionException(message: String) : Exception(message)

class ApiException(message: String, val statusCode: Int) : Exception(message)

class NetworkTimeoutException(message: String) : Exception(message)
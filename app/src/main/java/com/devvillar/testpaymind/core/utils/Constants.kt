package com.devvillar.testpaymind.core.utils

import com.devvillar.testpaymind.BuildConfig

object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    const val IS_PRODUCTION = BuildConfig.IS_PRODUCTION

    val CONNECT_TIMEOUT = if (BuildConfig.DEBUG) 60L else 30L
    val READ_TIMEOUT = if (BuildConfig.DEBUG) 60L else 30L
    val WRITE_TIMEOUT = if (BuildConfig.DEBUG) 60L else 30L

}
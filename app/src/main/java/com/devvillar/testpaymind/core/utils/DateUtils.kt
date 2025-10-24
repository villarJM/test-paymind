package com.devvillar.testpaymind.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    fun formatDateForApi(date: String): String {
        return try {
            val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val parsedDate = inputFormat.parse(date)
            val base = outputFormat.format(parsedDate!!)
            "$base+00:00:00"
        } catch (_: Exception) {
            date
        }
    }
}

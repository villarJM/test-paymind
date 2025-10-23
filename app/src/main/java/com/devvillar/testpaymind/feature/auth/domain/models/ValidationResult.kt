package com.devvillar.testpaymind.feature.auth.domain.models

data class ValidationResult(
    val errors: Map<String, String> = emptyMap()
) {
    val isValid: Boolean get() = errors.isEmpty()
    fun hasError(field: String): Boolean = errors.containsKey(field)
    fun getError(field: String): String? = errors[field]
}

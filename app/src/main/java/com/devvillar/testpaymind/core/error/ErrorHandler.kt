package com.devvillar.testpaymind.core.error

import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandler @Inject constructor() {

    companion object {
        private val gson = Gson()
    }

    fun getErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is UnknownHostException -> "Sin conexión a internet"
            is ConnectException -> "No se puede conectar al servidor"
            is SocketTimeoutException -> "Tiempo de espera agotado"
            is HttpException -> {
                val errorBody = throwable.response()?.errorBody()?.string()
                val parsedMessage = parseErrorMessage(errorBody)
                when (throwable.code()) {
                    400 -> parsedMessage ?: "Solicitud incorrecta"
                    401 -> parsedMessage ?: "No autorizado"
                    403 -> parsedMessage ?: "Acceso prohibido"
                    404 -> parsedMessage ?: "Recurso no encontrado"
                    422 -> parsedMessage ?: "Datos de entrada inválidos"
                    429 -> parsedMessage ?: "Demasiadas solicitudes"
                    500 -> parsedMessage ?: "Error interno del servidor"
                    502 -> parsedMessage ?: "Servidor no disponible"
                    503 -> parsedMessage ?: "Servicio temporalmente no disponible"
                    else -> parsedMessage ?: "HTTP error: ${throwable.code()} - ${throwable.message()}"
                }
            }
            else -> throwable.message ?: "Error desconocido"
        }
    }

    private fun parseErrorMessage(errorBody: String?): String? {
        return try {
            if (errorBody.isNullOrEmpty()) return null
            // Using shared Gson instance
            val errorResponse = gson.fromJson(errorBody, JsonObject::class.java)
            errorResponse["message"].toString()
        } catch (e: Exception) {
            null
        }
    }
}
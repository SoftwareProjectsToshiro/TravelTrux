package com.upao.travel_trux.repositories

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.upao.travel_trux.data.api.Apiclient
import com.upao.travel_trux.data.endpoints.ApiService
import com.upao.travel_trux.models.ApiError
import com.upao.travel_trux.models.requestModel.RegisterRequest
import com.upao.travel_trux.models.responseModel.ErrorResponse
import com.upao.travel_trux.models.responseModel.RegisterResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class UserRepository(context: Context) {

    suspend fun register(context: Context, user: RegisterRequest) : Boolean{
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.register(user)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val registerResponse = response.body()
                Toast.makeText(context, registerResponse?.msg, Toast.LENGTH_SHORT).show()
                true
            } else {
                val errorResponse = response.errorBody()?.string()
                val apiErrors = parseError(errorResponse)
                apiErrors?.let { errors ->
                    for (error in errors) {
                        val capitalizedCode = error.code.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                        Toast.makeText(
                            context,
                            "${capitalizedCode}: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } ?: run {
                    Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
                false
            }
        }
    }

    fun login(email: String, password: String): Boolean {
        return true
    }

    private fun parseError(errorBody: String?): List<ApiError>? {
        return try {
            errorBody?.let {
                val gson = Gson()
                val errorResponse = gson.fromJson(it, ErrorResponse::class.java)
                errorResponse.errors
            }
        } catch (e: Exception) {
            null
        }
    }
}
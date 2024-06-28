package com.upao.travel_trux.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.upao.travel_trux.data.api.Apiclient
import com.upao.travel_trux.data.endpoints.ApiService
import com.upao.travel_trux.models.ApiError
import com.upao.travel_trux.models.requestModel.ComentRequest
import com.upao.travel_trux.models.responseModel.ComentResponse
import com.upao.travel_trux.models.responseModel.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class ComentsRepository(context: Context) {
    suspend fun getComents(context: Context, id: Int) : List<ComentResponse> {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.getComents(id)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        }

    }

    suspend fun updateComent(context: Context, coment: ComentRequest): Boolean {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.comment(coment)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                val json = response.body()?.msg
                Log.d("JSON Response", json ?: "No JSON received")
                val commentResponse = response.body()
                Toast.makeText(context, commentResponse?.msg, Toast.LENGTH_SHORT).show()
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
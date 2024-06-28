package com.upao.travel_trux.repositories

import android.content.Context
import com.upao.travel_trux.data.api.Apiclient
import com.upao.travel_trux.data.endpoints.ApiService
import com.upao.travel_trux.models.responseModel.ComentResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
}
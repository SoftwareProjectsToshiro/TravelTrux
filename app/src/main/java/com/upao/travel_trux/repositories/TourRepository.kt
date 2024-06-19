package com.upao.travel_trux.repositories

import android.content.Context
import android.widget.Toast
import com.upao.travel_trux.data.api.Apiclient
import com.upao.travel_trux.data.endpoints.ApiService
import com.upao.travel_trux.models.responseModel.TourResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TourRepository(context: Context) {

    suspend fun getTours(context: Context, id: Int): List<TourResponse> {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.toursGet(id)
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        }
    }
}
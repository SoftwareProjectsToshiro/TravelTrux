package com.upao.travel_trux.repositories

import android.content.Context
import com.upao.travel_trux.data.api.Apiclient
import com.upao.travel_trux.data.endpoints.ApiService
import com.upao.travel_trux.models.responseModel.TourPackageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TourPackageRepository(context: Context) {

    suspend fun getTourPackages(context: Context): List<TourPackageResponse> {
        val apiService = Apiclient.createService(ApiService::class.java)
        val response = apiService.packagesGet()
        return withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                response.body()!!
            } else {
                emptyList()
            }
        }
    }
}
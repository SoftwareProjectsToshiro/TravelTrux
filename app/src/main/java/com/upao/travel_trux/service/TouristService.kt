package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.requestModel.TouristRequest
import com.upao.travel_trux.models.responseModel.TouristResponse
import com.upao.travel_trux.repositories.TouristRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TouristService(context: Context) {

    private val touristRepository = TouristRepository(context)

    fun registerTourist(context: Context, touristRequest: TouristRequest, onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = touristRepository.registerTourist(context, touristRequest)
            withContext(Dispatchers.Main) {
                onResult(isSuccess)
            }
        }
    }

    fun getTourist(context: Context, numDocument: String, onResult: (TouristResponse) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val tourist = touristRepository.getTourist(context, numDocument)
            withContext(Dispatchers.Main) {
                onResult(tourist)
            }
        }
    }
}
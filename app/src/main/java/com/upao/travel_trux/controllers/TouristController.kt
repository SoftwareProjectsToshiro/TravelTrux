package com.upao.travel_trux.controllers

import android.content.Context
import com.upao.travel_trux.models.requestModel.TouristRequest
import com.upao.travel_trux.models.responseModel.TouristResponse
import com.upao.travel_trux.service.TouristService

class TouristController(context: Context) {

    private val touristService = TouristService(context)

    fun registerTourist(context: Context, touristRequest: TouristRequest, onResult: (Boolean) -> Unit) {
        touristService.registerTourist(context, touristRequest) { isSuccess ->
            onResult(isSuccess)
        }
    }

    fun getTourist(context: Context, numDocument: String, onResult: (TouristResponse) -> Unit) {
        touristService.getTourist(context, numDocument) { tourist ->
            onResult(tourist)
        }
    }
}
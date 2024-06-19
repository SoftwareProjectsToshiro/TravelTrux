package com.upao.travel_trux.controllers

import android.content.Context
import com.upao.travel_trux.models.Tourist
import com.upao.travel_trux.service.TouristService

class TouristController(context: Context) {

    private val touristService = TouristService(context)

    fun registerTourist(context: Context, tourist: Tourist, onResult: (Boolean) -> Unit) {
        touristService.registerTourist(context, tourist) { isSuccess ->
            onResult(isSuccess)
        }
    }
}
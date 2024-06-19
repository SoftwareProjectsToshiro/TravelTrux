package com.upao.travel_trux.controllers

import android.content.Context
import com.upao.travel_trux.models.responseModel.TourResponse
import com.upao.travel_trux.service.TourService

class TourController(context: Context) {

    private val tourService = TourService(context)

    fun getTours(context: Context, id:Int, onResult: (List<TourResponse>) -> Unit) {
        tourService.getTours(context, id) { tours ->
            onResult(tours)
        }
    }
}
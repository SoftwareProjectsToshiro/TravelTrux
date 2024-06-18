package com.upao.travel_trux.controllers

import android.content.Context
import com.upao.travel_trux.models.responseModel.TourPackageResponse
import com.upao.travel_trux.service.TourPackageService

class TourPackageController(context: Context) {

    private val tourPackageService = TourPackageService(context)

    fun getTourPackages(context: Context, onResult: (List<TourPackageResponse>) -> Unit) {
        tourPackageService.getTourPackages(context) { tourPackages ->
            onResult(tourPackages)
        }
    }
}
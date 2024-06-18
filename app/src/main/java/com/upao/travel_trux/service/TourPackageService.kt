package com.upao.travel_trux.service

import android.content.Context
import com.upao.travel_trux.models.responseModel.TourPackageResponse
import com.upao.travel_trux.repositories.TourPackageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TourPackageService(context: Context) {

    private val tourPackageRepository = TourPackageRepository(context)

    fun getTourPackages(context: Context, onResult: (List<TourPackageResponse>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val tourPackages = tourPackageRepository.getTourPackages(context)
            withContext(Dispatchers.Main) {
                onResult(tourPackages)
            }
        }
    }
}